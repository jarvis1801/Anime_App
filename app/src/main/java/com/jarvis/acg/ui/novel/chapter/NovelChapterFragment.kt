package com.jarvis.acg.ui.novel.chapter

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseFragment
import com.jarvis.acg.databinding.FragmentNovelChapterBinding
import com.jarvis.acg.extension.ViewExtension.Companion.addClick
import com.jarvis.acg.extension.ViewExtension.Companion.addGlobalListenerAsOne
import com.jarvis.acg.util.DeviceUtil.getScreenHeight
import com.jarvis.acg.util.DeviceUtil.getScreenHeightIncludeStatusBar
import com.jarvis.acg.viewModel.MainViewModel
import com.jarvis.acg.viewModel.novel.NovelChapterViewModel


class NovelChapterFragment : BaseFragment<FragmentNovelChapterBinding, NovelChapterViewModel, MainViewModel>() {

    override var isFullScreen: Boolean = true
    override var isChildViewModelShare: Boolean = true

    override fun getArgs(): Bundle {
        val args: NovelChapterFragmentArgs by navArgs()
        return args.toBundle()
    }

    override fun getLayoutId(): Int = R.layout.fragment_novel_chapter

    override fun getViewModelClass(): Class<NovelChapterViewModel> = NovelChapterViewModel::class.java

    override fun getActivityViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun initView() {
    }

    override fun initListener() {
        getDataBinding().scrollView.setOnScrollChangeListener { view, scrollX, scrollY, oldX, oldY ->
            updateCurrentLine(scrollY)
            updateChapterIsRead(view)
            updateProgressBar(scrollY)
        }

        getDataBinding().imgPrevChapter.addClick({
            mViewModel?.onPrevChapterClick { getDataBinding().scrollView.scrollTo(0, 0) }
        }, 0)

        getDataBinding().imgNextChapter.addClick({
            mViewModel?.onNextChapterClick { getDataBinding().scrollView.scrollTo(0, 0) }
        }, 0)
    }

    private fun updateProgressBar(scrollY: Int) {
        getDataBinding().llContent.let { view ->
            val viewHeight = view.height
            val percent = (scrollY / (viewHeight - getScreenHeightIncludeStatusBar()).toDouble()).times(100)

            getDataBinding().progressBar.progress = percent.toInt()
        }
    }

    override fun initStartEvent() {
        observeViewModel()
        getArgs().getString("chapterId")?.let { id ->
            mViewModel?.setCurrentChapterById(id)
        }
    }

    private fun observeViewModel() {
        mViewModel?.currentChapter?.observe(viewLifecycleOwner) { chapter ->
            chapter?.let {
                mViewModel?.contentLineTopList?.clear()
                getDataBinding().tvContent.apply {
                    addGlobalListenerAsOne {
                        chapter.currentLine?.takeIf { it > 0 && it < layout.lineCount }?.let {
                            val textViewY = layout.getLineBottom(chapter.currentLine!!)
                            val totalY = textViewY + top
                            getDataBinding().scrollView.scrollTo(0, totalY)
                        }
                    }
                }
                getDataBinding().llContent.apply {
                    addGlobalListenerAsOne {
                        if (height < getScreenHeight()) mViewModel?.updateCurrentChapterIsRead(true)
                    }
                }
            }
        }
    }

    private fun updateChapterIsRead(view: View) {
        if (!view.canScrollVertically(1)) {
            mViewModel?.updateCurrentChapterIsRead(true)
        }
    }

    private fun updateCurrentLine(scrollY: Int) {
        if (getDataBinding().tvContent.height != 0) {
            getDataBinding().tvContent.apply {
                val contentLineTopList = mViewModel?.contentLineTopList ?: arrayListOf()
                if (contentLineTopList.isNullOrEmpty()) {
                    contentLineTopList.clear()
                    for (x in 0 until layout.lineCount) {
                        contentLineTopList.add(layout.getLineBottom(x))
                    }
                } else {
                    val tvContentScrolledY = scrollY - getDataBinding().tvContent.top
                    if (tvContentScrolledY > 0) {
                        contentLineTopList.indexOfFirst { it > tvContentScrolledY }.let {
                            if (mViewModel?.currentLine != it) {
                                mViewModel?.currentLine = it
                                mViewModel?.updateCurrentChapterCurrentLine(it)
                            }
                        }
                    }
                }
            }
        }
    }
}