package com.jarvis.acg.ui.novel.chapter

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseFragment
import com.jarvis.acg.databinding.FragmentNovelChapterBinding
import com.jarvis.acg.viewModel.MainViewModel
import com.jarvis.acg.viewModel.novel.NovelChapterViewModel

class NovelChapterFragment : BaseFragment<FragmentNovelChapterBinding, NovelChapterViewModel, MainViewModel>() {

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

    }

    override fun initStartEvent() {
        observeViewModel()
    }

    private fun observeViewModel() {
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}