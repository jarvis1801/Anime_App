package com.jarvis.acg.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

abstract class BaseSingleRecyclerViewAdapter<DB: ViewDataBinding, VH: BaseSingleViewHolder<D>, D: Any>(context: Context)
    : BaseRevampRecyclerViewAdapter(context) {
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> DB
    private lateinit var mViewDataBinding: DB
    abstract fun getViewHolderClass(databinding: DB): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        mViewDataBinding = bindingInflater.invoke(LayoutInflater.from(context), parent, false)
        return getViewHolderClass(mViewDataBinding)
    }

    protected fun getViewDataBinding(): DB {
        return mViewDataBinding
    }

}