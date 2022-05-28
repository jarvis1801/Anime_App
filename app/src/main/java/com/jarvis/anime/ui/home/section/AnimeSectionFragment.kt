package com.jarvis.anime.ui.home.section

import com.jarvis.anime.R
import com.jarvis.anime.base.BaseFragment
import com.jarvis.anime.databinding.FragmentAnimeSectionBinding
import com.jarvis.anime.viewModel.EmptyViewModel
import com.jarvis.anime.viewModel.MainViewModel

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