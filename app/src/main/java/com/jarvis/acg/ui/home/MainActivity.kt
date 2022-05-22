package com.jarvis.acg.ui.home

import android.content.Intent
import android.view.View
import androidx.navigation.findNavController
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseActivity
import com.jarvis.acg.databinding.ActivityHomeBinding
import com.jarvis.acg.service.MangaChapterDownloadService
import com.jarvis.acg.service.MangaChapterDownloadService.Companion.KEY_IMAGE_ID_LIST
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

        mViewModel?.prefetchImageIdList?.observe(this) { list ->
            takeIf { !list.isNullOrEmpty() }?.let {
                startDownloadService(list)
            }
        }
    }

    private fun startDownloadService(list: ArrayList<String>) {
        startService(Intent(this, MangaChapterDownloadService::class.java).apply {
            putExtra(KEY_IMAGE_ID_LIST, list)
        })
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