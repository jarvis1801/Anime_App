package com.jarvis.acg.ui.home.section

import com.jarvis.acg.BR
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseFragment
import com.jarvis.acg.databinding.FragmentMangaSectionBinding
import com.jarvis.acg.viewModel.EmptyViewModel
import com.jarvis.acg.viewModel.MainViewModel
import com.jarvis.acg.viewModel.home.HomeViewModel

class MangaSectionFragment : BaseFragment<FragmentMangaSectionBinding, EmptyViewModel, MainViewModel>() {

    override fun getLayoutId(): Int { return R.layout.fragment_manga_section }

    override fun getViewModelClass(): Class<EmptyViewModel> { return EmptyViewModel::class.java }

    override fun getActivityViewModelClass(): Class<MainViewModel> { return MainViewModel::class.java }

    override fun initView() {
    }

    override fun initListener() {
    }

    override fun initStartEvent() {
    }
}