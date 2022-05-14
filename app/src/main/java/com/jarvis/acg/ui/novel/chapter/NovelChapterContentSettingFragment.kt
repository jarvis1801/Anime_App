package com.jarvis.acg.ui.novel.chapter

import com.jarvis.acg.R
import com.jarvis.acg.base.BaseFragment
import com.jarvis.acg.databinding.FragmentNovelContentSettingBinding
import com.jarvis.acg.viewModel.EmptyViewModel
import com.jarvis.acg.viewModel.MainViewModel

class NovelChapterContentSettingFragment : BaseFragment<FragmentNovelContentSettingBinding, EmptyViewModel, MainViewModel>() {
    override fun getLayoutId(): Int = R.layout.fragment_novel_content_setting

    override fun getViewModelClass(): Class<EmptyViewModel> = EmptyViewModel::class.java

    override fun getActivityViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun initView() {
        getDataBinding().fontPanel.apply {
            onUpdateCallback = {
                this@NovelChapterContentSettingFragment.updateTextView()
            }
        }
    }

    private fun updateTextView() {
        getDataBinding().tvTitle.updateFontStyle()
        getDataBinding().tvContent.updateFontStyle()
    }

    override fun initListener() {
    }

    override fun initStartEvent() {
    }

}