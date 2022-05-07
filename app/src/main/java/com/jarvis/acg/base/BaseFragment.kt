package com.jarvis.acg.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jarvis.acg.BR
import com.jarvis.acg.extension.ViewExtension.Companion.hideStatusBar
import com.jarvis.acg.extension.ViewExtension.Companion.showStatusBar
import com.jarvis.acg.ui.home.MainActivity
import com.jarvis.acg.viewModel.MainViewModel
import com.jarvis.acg.viewModel.ViewModelFactory

abstract class BaseFragment<DB : ViewDataBinding, VM: BaseViewModel, AVM: MainViewModel> : Fragment() {
    private lateinit var mViewDataBinding: DB
    var mViewModel: VM? = null
    var mActivityViewModel: AVM? = null

    open var isFullScreen = false

    open var isChildViewModelShare = false

    @LayoutRes abstract fun getLayoutId(): Int

    abstract fun getViewModelClass(): Class<VM>?
    abstract fun getActivityViewModelClass(): Class<AVM>?
    open fun subscribeViewModel() {
        mViewModel?.requestLoading?.observe(viewLifecycleOwner) {
            if (it == true) {
                showLoading()
            }
        }

        mViewModel?.reduceLoading?.observe(viewLifecycleOwner) {
            if (it == true) {
                hideLoading()
            }
        }
    }

    private fun showLoading() {
        val mainActivity = requireActivity() as MainActivity
        mainActivity.showLoading()
    }

    private fun hideLoading() {
        val mainActivity = requireActivity() as MainActivity
        mainActivity.hideLoading()
    }

    open fun getArgs(): Bundle { return Bundle() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (isFullScreen) hideStatusBar()
        initViewModel()
        subscribeViewModel()
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return mViewDataBinding.root
    }

    private fun initViewModel() {
        val viewModelFactory = ViewModelFactory(this, getArgs())
        getViewModelClass().takeIf { it != null }?.let { clazz -> mViewModel = ViewModelProvider(if (isChildViewModelShare) requireActivity() else this, viewModelFactory)[clazz] }
        getActivityViewModelClass().takeIf { it != null }?.let { clazz -> mActivityViewModel = ViewModelProvider(requireActivity())[clazz] }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        performDataBinding()
        initView()
        initListener()
        initStartEvent()
    }

    abstract fun initView()
    abstract fun initListener()
    abstract fun initStartEvent()

    private fun performDataBinding() {
        mViewDataBinding.setVariable(BR.viewModel, mViewModel)
        mViewDataBinding.setVariable(BR.activityViewModel, mActivityViewModel)
        mViewDataBinding.lifecycleOwner = viewLifecycleOwner
        mViewDataBinding.executePendingBindings()
    }

    fun getDataBinding(): DB {
        return mViewDataBinding
    }

    override fun onDestroy() {
        if (isFullScreen) showStatusBar()
        super.onDestroy()
    }
}