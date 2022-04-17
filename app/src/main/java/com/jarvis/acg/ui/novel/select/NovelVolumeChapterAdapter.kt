package com.jarvis.acg.ui.novel.select

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.jarvis.acg.BR
import com.jarvis.acg.base.BaseRevampRecyclerViewAdapter
import com.jarvis.acg.base.BaseRevampViewHolder
import com.jarvis.acg.databinding.ItemNovelSelectChapterBinding
import com.jarvis.acg.databinding.ItemNovelSelectVolumeBinding
import com.jarvis.acg.extension.ViewExtension.Companion.addClick
import com.jarvis.acg.model.Chapter
import com.jarvis.acg.model.Volume

class NovelVolumeChapterAdapter(context: Context, val onChapterClick: (chapter: Chapter) -> Unit) : BaseRevampRecyclerViewAdapter(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_VOLUME -> {
                val mViewDataBinding = ItemNovelSelectVolumeBinding.inflate(
                    LayoutInflater.from(context), parent, false)
                VolumeViewHolder(mViewDataBinding)
            }
            TYPE_CHAPTER -> {
                val mViewDataBinding = ItemNovelSelectChapterBinding.inflate(
                    LayoutInflater.from(context), parent, false)
                ChapterViewHolder(mViewDataBinding)
            }
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataList[position]) {
            is Volume -> TYPE_VOLUME
            is Chapter -> TYPE_CHAPTER
            else -> -TYPE_UNKNOWN
        }
    }

    inner class VolumeViewHolder(val binding: ViewDataBinding) : BaseRevampViewHolder<Volume>(binding.root) {
        override fun onBind(position: Int, item: Volume) {
            binding.setVariable(BR.volume, item)
            binding.executePendingBindings()
        }
    }

    inner class ChapterViewHolder(val binding: ViewDataBinding) : BaseRevampViewHolder<Chapter>(binding.root) {
        override fun onBind(position: Int, item: Chapter) {
            binding.setVariable(BR.chapter, item)
            binding.executePendingBindings()
            if (binding is ItemNovelSelectChapterBinding) {
                binding.chipChapter.apply {
                    addClick({
                        onChapterClick(item)
                    })
                }
            }
        }
    }

    companion object {
        private const val TYPE_VOLUME = 0
        private const val TYPE_CHAPTER = 1
    }
}