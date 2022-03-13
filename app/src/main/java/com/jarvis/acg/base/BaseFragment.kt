package com.jarvis.acg.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<DB : ViewDataBinding, VM: BaseViewModel<*>, AVM: BaseViewModel<*>> : Fragment() {
    private lateinit var mViewDataBinding: DB
    var mViewModel: VM? = null
    var mActivityViewModel: AVM? = null

    abstract fun getBindingVariable(): Int
    @LayoutRes abstract fun getLayoutId(): Int

    abstract fun getViewModelClass(): Class<VM>?
    abstract fun getActivityViewModelClass(): Class<AVM>?

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getViewModelClass().takeIf { it != null }?.let { clazz -> mViewModel = ViewModelProvider(requireActivity())[clazz] }
        getActivityViewModelClass().takeIf { it != null }?.let { clazz -> mActivityViewModel = ViewModelProvider(requireActivity())[clazz] }
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return mViewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        performDataBinding()
    }

    private fun performDataBinding() {
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.executePendingBindings()
    }

    fun getDataBinding(): DB {
        return mViewDataBinding
    }
}