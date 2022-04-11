package com.jarvis.acg.ui.home.section

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jarvis.acg.R
import com.jarvis.acg.BR
import com.jarvis.acg.base.BaseFragment
import com.jarvis.acg.databinding.FragmentAnimeSectionBinding
import com.jarvis.acg.viewModel.EmptyViewModel
import com.jarvis.acg.viewModel.home.HomeViewModel

class AnimeSectionFragment : BaseFragment<FragmentAnimeSectionBinding, EmptyViewModel, HomeViewModel>() {
    override fun getBindingVariable(): Int { return BR.viewModel }

    override fun getLayoutId(): Int { return R.layout.fragment_anime_section }

    override fun getViewModelClass(): Class<EmptyViewModel> { return EmptyViewModel::class.java }

    override fun getActivityViewModelClass(): Class<HomeViewModel> { return HomeViewModel::class.java }

}