package com.jarvis.acg.ui.home.section.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.jarvis.acg.base.BaseSingleRecyclerViewAdapter
import com.jarvis.acg.base.BaseSingleViewHolder
import com.jarvis.acg.databinding.ItemNovelSectionBinding
import com.jarvis.acg.extension.ViewExtension.Companion.addClick
import com.jarvis.acg.model.Work
import com.jarvis.acg.util.NavigationUtil.gotoNovelSelectChapterFragment

class NovelAdapter(context: Context) : BaseSingleRecyclerViewAdapter<ItemNovelSectionBinding, NovelAdapter.ViewHolder, Work>(context) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemNovelSectionBinding
        get() = ItemNovelSectionBinding::inflate

    override fun getViewHolderClass(databinding: ItemNovelSectionBinding): ViewHolder {
        return ViewHolder(databinding)
    }

    inner class ViewHolder(binding: ViewDataBinding): BaseSingleViewHolder<Work>(binding) {
        override fun onBind(position: Int, item: Work) {
            super.onBind(position, item)
            getViewDataBinding().root.apply {
                addClick({ gotoNovelSelectChapterFragment(item) })
            }
        }
    }

}