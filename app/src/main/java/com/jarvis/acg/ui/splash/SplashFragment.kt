package com.jarvis.acg.ui.splash

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseFragment
import com.jarvis.acg.databinding.FragmentSplashBinding
import com.jarvis.acg.ui.home.MainActivity
import com.jarvis.acg.util.NavigationUtil.gotoHomeFragment
import com.jarvis.acg.viewModel.MainViewModel

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