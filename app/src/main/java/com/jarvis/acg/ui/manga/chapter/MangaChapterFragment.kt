package com.jarvis.acg.ui.manga.chapter

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseFragment
import com.jarvis.acg.databinding.FragmentMangaChapterBinding
import com.jarvis.acg.util.ReadingModeUtil
import com.jarvis.acg.viewModel.MainViewModel
import com.jarvis.acg.viewModel.manga.MangaChapterViewModel

class MangaChapterFragment : BaseFragment<FragmentMangaChapterBinding, MangaChapterViewModel, MainViewModel>() {
    private var mangaContentAdapter: MangaContentAdapter? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var snapHelper: PagerSnapHelper? = null

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
            }
        }
    }

    override fun initView() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        snapHelper = PagerSnapHelper()
        mangaContentAdapter = MangaContentAdapter(requireContext())
        linearLayoutManager = LinearLayoutManager(requireContext())
        getDataBinding().rvChapterList.apply {
            adapter = mangaContentAdapter
            layoutManager = linearLayoutManager
        }

        updateReadingMode()
    }

    private fun updateReadingMode() {
        takeIf { snapHelper != null && linearLayoutManager != null }.let {
            ReadingModeUtil().changeType(requireContext(), snapHelper!!, linearLayoutManager!!, getDataBinding().rvChapterList)
        }
    }

    override fun initListener() {

    }

    override fun initStartEvent() {
        getArgs().getString("chapterId")?.let { id ->
            mViewModel?.setCurrentMangaChapterById(id)
        }
    }

}