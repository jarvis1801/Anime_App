package com.jarvis.acg.base

import androidx.databinding.ViewDataBinding
import com.jarvis.acg.BR

open class BaseSingleViewHolder<D>(private val binding: ViewDataBinding) : BaseRevampViewHolder<D>(binding.root) {

    override fun onBind(position: Int, item: D) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}