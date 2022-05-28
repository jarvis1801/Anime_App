package com.jarvis.anime.base

import androidx.databinding.ViewDataBinding
import com.jarvis.anime.BR

open class BaseSingleViewHolder<D>(private val binding: ViewDataBinding) : BaseRevampViewHolder<D>(binding.root) {

    override fun onBind(position: Int, item: D) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}