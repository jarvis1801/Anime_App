package com.jarvis.acg.ui.home.section

import com.jarvis.acg.BR
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseFragment
import com.jarvis.acg.databinding.FragmentNovelSectionBinding
import com.jarvis.acg.viewModel.EmptyViewModel
import com.jarvis.acg.viewModel.home.HomeViewModel

class NovelSectionFragment : BaseFragment<FragmentNovelSectionBinding, EmptyViewModel, HomeViewModel>() {
    override fun getBindingVariable(): Int { return BR.viewModel }

    override fun getLayoutId(): Int { return R.layout.fragment_novel_section }

    override fun getViewModelClass(): Class<EmptyViewModel> { return EmptyViewModel::class.java }

    override fun getActivityViewModelClass(): Class<HomeViewModel> { return HomeViewModel::class.java }

}