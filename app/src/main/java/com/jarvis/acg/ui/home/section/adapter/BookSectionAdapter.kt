package com.jarvis.acg.ui.home.section.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.jarvis.acg.base.BaseSingleRecyclerViewAdapter
import com.jarvis.acg.base.BaseSingleViewHolder
import com.jarvis.acg.databinding.ItemBookSectionBinding
import com.jarvis.acg.extension.ViewExtension.Companion.addClick
import com.jarvis.acg.model.Work
import com.jarvis.acg.util.NavigationUtil.gotoNovelSelectChapterFragment

class BookSectionAdapter(context: Context, val onClick: (item: Work) -> Unit = {}) :
    BaseSingleRecyclerViewAdapter<ItemBookSectionBinding, BookSectionAdapter.ViewHolder, Work>(context) {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemBookSectionBinding
        get() = ItemBookSectionBinding::inflate

    override fun getViewHolderClass(databinding: ItemBookSectionBinding): ViewHolder {
        return ViewHolder(databinding)
    }

    inner class ViewHolder(binding: ViewDataBinding): BaseSingleViewHolder<Work>(binding) {
        override fun onBind(position: Int, item: Work) {
            super.onBind(position, item)
            getViewDataBinding().root.apply {
                addClick({ onClick(item) })
            }
        }
    }

}