package com.jarvis.acg.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider


abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel<*>> : AppCompatActivity() {
    private var mViewDataBinding: DB? = null
    var mViewModel: VM? = null

    abstract fun getBindingVariable(): Int
    @LayoutRes abstract fun getLayoutId(): Int

    protected abstract fun getViewModelClass(): Class<VM>

    abstract fun initView()
    abstract fun initListener()
    abstract fun initStartEvent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProvider(this)[getViewModelClass()]

        performDataBinding()
        initView()
        initListener()
        initStartEvent()
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewDataBinding?.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding?.executePendingBindings()
    }

    fun getDataBinding(): DB {
        return mViewDataBinding!!
    }


    fun showDialog(dialog: DialogFragment, tag: String) {
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            dialog.show(supportFragmentManager, tag)
        }
    }
}