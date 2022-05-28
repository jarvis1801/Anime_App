package com.jarvis.anime.ui.home.section.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.jarvis.anime.base.BaseSingleRecyclerViewAdapter
import com.jarvis.anime.base.BaseSingleViewHolder
import com.jarvis.anime.databinding.ItemBookSectionBinding
import com.jarvis.anime.extension.ViewExtension.Companion.addClick
import com.jarvis.anime.model.Work
import com.jarvis.anime.util.GlideUtil.loadImage

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
            getViewDataBinding().apply {
                root.apply {
                    addClick({ onClick(item) })
                }

                imgThumbnail.apply {
                    item.image_byte_list.takeIf { !it.isNullOrEmpty() }?.get(0)?.let { imageString ->
                        loadImage(imageString)
                    }
                }
            }
        }
    }

}