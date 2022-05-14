package com.jarvis.acg.ui.home

import android.view.View
import androidx.navigation.findNavController
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseActivity
import com.jarvis.acg.databinding.ActivityHomeBinding
import com.jarvis.acg.viewModel.MainViewModel

class MainActivity : BaseActivity<ActivityHomeBinding, MainViewModel>() {

    override fun getLayoutId(): Int { return R.layout.activity_home }

    override fun getViewModelClass(): Class<MainViewModel> { return MainViewModel::class.java }

    override fun subscribeViewModel() {
        mViewModel?.requestLoading?.observe(this) {
            if (it == true) {
                showLoading()
            }
        }

        mViewModel?.reduceLoading?.observe(this) {
            if (it == true) {
                hideLoading()
            }
        }
    }

    override fun initView() {
    }

    override fun initListener() {}

    override fun initStartEvent() {
    }

    fun showLoading() {
        getDataBinding().loadingFrame.showLoading()
    }

    fun hideLoading() {
        getDataBinding().loadingFrame.hideLoading()
    }

    private fun isInterceptBack(): Boolean {
        return getDataBinding().loadingFrame.visibility == View.VISIBLE
    }

    override fun onBackPressed() {
        if (findNavController(R.id.nav_host_fragment).currentBackStackEntry?.destination?.id == R.id.homeFragment) {
            finishAffinity()
        } else {
            if (!isInterceptBack())
                super.onBackPressed()
        }
    }
}