package com.jarvis.acg.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.jarvis.acg.base.BaseSingleRecyclerViewAdapter
import com.jarvis.acg.base.BaseSingleViewHolder
import com.jarvis.acg.databinding.ItemReadingModeListBinding
import com.jarvis.acg.extension.ViewExtension.Companion.addClick

class ReadingModeAdapter(context: Context, val onClick: (position: Int) -> Unit)
    : BaseSingleRecyclerViewAdapter<ItemReadingModeListBinding, ReadingModeAdapter.ViewHolder, String>(context) {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemReadingModeListBinding
        get() = ItemReadingModeListBinding::inflate

    override fun getViewHolderClass(databinding: ItemReadingModeListBinding): ViewHolder {
        return ViewHolder(databinding)
    }

    inner class ViewHolder(val binding: ViewDataBinding) : BaseSingleViewHolder<String>(binding) {
        override fun onBind(position: Int, item: String) {
            super.onBind(position, item)
            binding.root.addClick({ onClick(position) })
        }
    }
}