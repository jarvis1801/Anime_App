package com.jarvis.anime.ui.splash

import android.os.Bundle
import com.jarvis.anime.R
import com.jarvis.anime.base.BaseFragment
import com.jarvis.anime.databinding.FragmentSplashBinding
import com.jarvis.anime.util.NavigationUtil.gotoHomeFragment
import com.jarvis.anime.viewModel.MainViewModel

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel, MainViewModel>() {

    override fun getLayoutId(): Int { return R.layout.fragment_splash }

    override fun getViewModelClass(): Class<SplashViewModel> { return SplashViewModel::class.java }

    override fun getActivityViewModelClass(): Class<MainViewModel> { return MainViewModel::class.java }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
    }

    override fun initListener() {
    }

    override fun initStartEvent() {
        gotoHomeFragment()
    }

//    override fun openHomeActivity() { launchActivity<MainActivity>() }
//
//    override fun openUpdateDialog() {
//        // TODO update dialog
//        val dialog = GeneralDialog().apply {
//            title = "Update"
//            message = "A new version"
//            positiveButtonText = "OK"
//        }
//        showDialog(dialog, GeneralDialog.TAG_UPDATE_DIALOG)
//    }

}