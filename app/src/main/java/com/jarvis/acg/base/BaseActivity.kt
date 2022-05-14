package com.jarvis.acg.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.jarvis.acg.BR
import com.jarvis.acg.util.LanguageUtil
import com.jarvis.acg.viewModel.ViewModelFactory


abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    private var mViewDataBinding: DB? = null
    var mViewModel: VM? = null

    @LayoutRes abstract fun getLayoutId(): Int

    protected abstract fun getViewModelClass(): Class<VM>

    abstract fun initView()
    abstract fun initListener()
    abstract fun initStartEvent()
    open fun subscribeViewModel() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = ViewModelFactory(this, intent.extras)
        mViewModel = ViewModelProvider(this, viewModelFactory)[getViewModelClass()]
        subscribeViewModel()

        performDataBinding()
        initView()
        initListener()
        initStartEvent()
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewDataBinding?.setVariable(BR.viewModel, mViewModel)
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

    fun getFragmentStack(): Int {
        return supportFragmentManager.backStackEntryCount
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LanguageUtil.createContextForLangChange(base))
    }
}