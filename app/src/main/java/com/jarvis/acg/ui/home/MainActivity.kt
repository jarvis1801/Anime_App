package com.jarvis.acg.ui.home

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseActivity
import com.jarvis.acg.databinding.ActivityHomeBinding
import com.jarvis.acg.viewModel.MainViewModel

class MainActivity : BaseActivity<ActivityHomeBinding, MainViewModel>() {

    override fun getLayoutId(): Int { return R.layout.activity_home }

    override fun getViewModelClass(): Class<MainViewModel> { return MainViewModel::class.java }

    override fun subscribeViewModel() {
        mViewModel?.requestLoading?.observe(this) {
            Log.d("chris", "requestLoading $it")
            if (it == true) {
                showLoading()
            }
        }

        mViewModel?.reduceLoading?.observe(this) {
            Log.d("chris", "reduceLoading $it")
            if (it == true) {
                hideLoading()
            }
        }

        mViewModel?.novelWorkList?.observe(this) {
            mViewModel?.requestApiFinished()
        }

        mViewModel?.mangaWorkList?.observe(this) {
            mViewModel?.requestApiFinished()
        }
    }

    override fun initView() {
    }

    override fun initListener() {}

    override fun initStartEvent() {
        mViewModel?.fetchList()
    }

    fun showLoading() {
        getDataBinding().loadingFrame.showLoading()
    }

    fun hideLoading() {
        getDataBinding().loadingFrame.hideLoading()
    }

    override fun onBackPressed() {
        if (findNavController(R.id.nav_host_fragment).currentBackStackEntry?.destination?.id == R.id.homeFragment) {
            finishAffinity()
        } else {
            super.onBackPressed()
        }
    }
}