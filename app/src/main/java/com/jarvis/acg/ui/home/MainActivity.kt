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

    override fun initView() {
    }

    override fun initListener() {}

    override fun initStartEvent() {
    }

    override fun onBackPressed() {
        if (findNavController(R.id.nav_host_fragment).currentBackStackEntry?.destination?.id == R.id.homeFragment) {
            finishAffinity()
        } else {
            super.onBackPressed()
        }
    }
}