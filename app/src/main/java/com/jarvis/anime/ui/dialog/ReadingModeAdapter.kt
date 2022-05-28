package com.jarvis.anime.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.jarvis.anime.base.BaseSingleRecyclerViewAdapter
import com.jarvis.anime.base.BaseSingleViewHolder
import com.jarvis.anime.databinding.ItemReadingModeListBinding
import com.jarvis.anime.extension.ViewExtension.Companion.addClick

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