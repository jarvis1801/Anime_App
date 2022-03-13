package com.jarvis.acg.ui.splash

import android.os.Bundle
import com.jarvis.acg.BR
import com.jarvis.acg.Extension.Companion.launchActivity
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseActivity
import com.jarvis.acg.databinding.ActivitySplashBinding
import com.jarvis.acg.ui.dialog.GeneralDialog
import com.jarvis.acg.ui.home.HomeActivity

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashNavigator {

    override fun getBindingVariable(): Int { return BR.viewModel }

    override fun getLayoutId(): Int { return R.layout.activity_splash }

    override fun getViewModelClass(): Class<SplashViewModel> { return SplashViewModel::class.java }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        mViewModel?.setNavigator(this)
    }

    override fun initListener() {}

    override fun initStartEvent() {
        mViewModel?.fetchConfig()
    }

    override fun openHomeActivity() { launchActivity<HomeActivity>() }

    override fun openUpdateDialog() {
        // TODO update dialog
        val dialog = GeneralDialog().apply {
            title = "Update"
            message = "A new version"
            positiveButtonText = "OK"
        }
        showDialog(dialog, GeneralDialog.TAG_UPDATE_DIALOG)
    }

}