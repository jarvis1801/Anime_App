package com.jarvis.acg.ui.manga.chapter

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.PagerSnapHelper
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseFragment
import com.jarvis.acg.databinding.BottomPanelChapterContentBinding
import com.jarvis.acg.databinding.FragmentMangaChapterBinding
import com.jarvis.acg.extension.Extension.Companion.toPx
import com.jarvis.acg.extension.ViewExtension.Companion.addClick
import com.jarvis.acg.model.mangaChapter.MangaChapterPage
import com.jarvis.acg.repository.resource.ResourceRemoteDataSource
import com.jarvis.acg.ui.dialog.ReadingModeBottomSheetDialogFragment
import com.jarvis.acg.util.AnimationUtil.addAnimatorListenerAdapter
import com.jarvis.acg.util.DeviceUtil
import com.jarvis.acg.util.ReadingModeUtil
import com.jarvis.acg.viewModel.MainViewModel
import com.jarvis.acg.viewModel.manga.MangaChapterViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MangaChapterFragment : BaseFragment<FragmentMangaChapterBinding, MangaChapterViewModel, MainViewModel>() {
    private var mangaContentAdapter: MangaContentAdapter? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var snapHelper: PagerSnapHelper? = null

    private var bottomPanelContainerBinding: BottomPanelChapterContentBinding? = null

    override fun getArgs(): Bundle {
        val args: MangaChapterFragmentArgs by navArgs()
        return args.toBundle()
    }

    override fun getLayoutId(): Int = R.layout.fragment_manga_chapter

    override fun getViewModelClass(): Class<MangaChapterViewModel> = MangaChapterViewModel::class.java

    override fun getActivityViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override var isChildViewModelShare: Boolean = true
    override var isFullScreen: Boolean = true

    override fun subscribeViewModel() {
        super.subscribeViewModel()

        mViewModel?.currentChapter?.observe(viewLifecycleOwner) { mangaChapter ->
            mangaChapter?.imageList?.let { imageList ->
                mangaContentAdapter?.clearData()
                mangaContentAdapter?.addAllData(imageList)
                mViewModel?.setCurrentPage(mangaChapter.lastPosition ?: 0, MangaChapterPage.CHAPTER_PAGE_SCROLL)
                updateProgressBarPage(imageList.size)
            }
        }

        mViewModel?.page?.observe(this) { pageObject ->
            val currentPage = pageObject?.page
            val adapterItemSize = mangaContentAdapter?.getItemsSize()

            takeIf { currentPage != null && adapterItemSize != null }?.let {
                if (currentPage!! <= adapterItemSize!! && currentPage >= 0) {
                    updatePageUI(currentPage, pageObject.scrollMode)
                    mViewModel?.updateChapterLastPosition(currentPage)
                }
            }
        }

        mViewModel?.notifyIndexChanged?.observe(viewLifecycleOwner) {
            if (it != null && it > 0 && it < mangaContentAdapter?.getItemsSize() ?: -1) {
                mangaContentAdapter?.notifyItemChanged(it)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updatePageUI(page: Int, scrollMode: Int) {
        bottomPanelContainerBinding?.progressbarPage?.value = page.toFloat()

        mViewModel?.getImageCount().let { imageCount ->
            bottomPanelContainerBinding?.tvPage?.text = "${page.plus(1)}/$imageCount"
        }
        when (scrollMode) {
            MangaChapterPage.CHAPTER_PAGE_SCROLL -> linearLayoutManager?.scrollToPositionWithOffset(page, 0)
            MangaChapterPage.CHAPTER_PAGE_SMOOTH_SCROLL -> {
                linearLayoutManager?.startSmoothScroll(object : LinearSmoothScroller(requireContext()) {
                    override fun getHorizontalSnapPreference(): Int { return SNAP_TO_START }
                }.apply { targetPosition = page })
            }
            MangaChapterPage.CHAPTER_PAGE_NON_SCROLL -> {}
        }
    }

    override fun initView() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        snapHelper = PagerSnapHelper()
        val screenWidth = DeviceUtil.getScreenWidth()
        val screenHeight = DeviceUtil.getScreenHeight()
        mangaContentAdapter = MangaContentAdapter(requireContext(), screenWidth, screenHeight)
        linearLayoutManager = LinearLayoutManager(requireContext())
        getDataBinding().rvChapterList.apply {
            setHasFixedSize(true)
            adapter = mangaContentAdapter
            layoutManager = linearLayoutManager
            setScrollChange(idleCallback = {
                val page = linearLayoutManager?.findLastVisibleItemPosition()
                val currentPage = mViewModel?.getCurrentPage()
                if (page != null && currentPage != page)
                    mViewModel?.setCurrentPage(page, MangaChapterPage.CHAPTER_PAGE_NON_SCROLL)
            })
        }

        updateReadingMode()
    }

    private fun updateReadingMode() {
        takeIf { snapHelper != null && linearLayoutManager != null }.let {
            ReadingModeUtil().changeType(snapHelper!!, linearLayoutManager!!, getDataBinding().rvChapterList)
        }
    }

    override fun initListener() {
        setupBottomBar()
    }

    private fun setupBottomBar() {
        getDataBinding().container.onClickCenter = {
            val bottomBar = getDataBinding().flBottomPanelContainer
            if (bottomBar.isEnabled) {
                val isShow = when (bottomBar.visibility) {
                    View.VISIBLE -> false
                    else -> true
                }

                val transitionY: Float = if (isShow) { 0f } else { 60.toPx }

                val animator = ObjectAnimator.ofFloat(bottomBar, View.TRANSLATION_Y, transitionY)
                animator.duration = 500
                bottomBar.addAnimatorListenerAdapter(animator, isShow)
                animator.start()
            }
        }

        bottomPanelContainerBinding = getDataBinding().bottomPanelContainer
        bottomPanelContainerBinding?.imgReadingMode?.addClick({
            val fragment = ReadingModeBottomSheetDialogFragment {
                updateReadingMode()
            }
            fragment.show(childFragmentManager, "ReadingModeBottomSheetDialogFragment")
        })
    }

    override fun initStartEvent() {
        getArgs().getString("chapterId")?.let { id ->
            mViewModel?.setCurrentMangaChapterById(id)
        }
    }

    private fun updateProgressBarPage(imageCount: Int) {
        bottomPanelContainerBinding?.progressbarPage?.apply {
            totalCount = imageCount
            setOnChangeListener { newPage ->
                val currentPage = mViewModel?.getCurrentPage()
                if (currentPage != newPage) {
                    mViewModel?.setCurrentPage(newPage, MangaChapterPage.CHAPTER_PAGE_SMOOTH_SCROLL)
                    mViewModel?.enableIsReadWhenLastPage(newPage + 1, imageCount)
                }
            }
        }
    }

    override fun onDestroy() {
        mViewModel?.resetViewModelForChapterPage()
        super.onDestroy()
    }
}