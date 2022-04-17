package com.jarvis.acg.ui.home.section

import com.jarvis.acg.BR
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseFragment
import com.jarvis.acg.databinding.FragmentAnimeSectionBinding
import com.jarvis.acg.viewModel.EmptyViewModel
import com.jarvis.acg.viewModel.MainViewModel

class AnimeSectionFragment : BaseFragment<FragmentAnimeSectionBinding, EmptyViewModel, MainViewModel>() {

    override fun getLayoutId(): Int { return R.layout.fragment_anime_section }

    override fun getViewModelClass(): Class<EmptyViewModel> { return EmptyViewModel::class.java }

    override fun getActivityViewModelClass(): Class<MainViewModel> { return MainViewModel::class.java }

    override fun initView() {
    }

    override fun initListener() {
    }

    override fun initStartEvent() {
    }
}