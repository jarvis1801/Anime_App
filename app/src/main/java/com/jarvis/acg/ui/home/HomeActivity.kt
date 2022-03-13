package com.jarvis.acg.ui.home

import com.jarvis.acg.BR
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseActivity
import com.jarvis.acg.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeNavigator {

    override fun getBindingVariable(): Int { return BR.viewModel }

    override fun getLayoutId(): Int { return R.layout.activity_home }

    override fun getViewModelClass(): Class<HomeViewModel> { return HomeViewModel::class.java }
}