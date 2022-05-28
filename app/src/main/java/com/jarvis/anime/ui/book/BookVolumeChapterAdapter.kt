package com.jarvis.anime.ui.book

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jarvis.anime.BR
import com.jarvis.anime.base.BaseRevampRecyclerViewAdapter
import com.jarvis.anime.base.BaseRevampViewHolder
import com.jarvis.anime.databinding.ItemBookSelectChapterBinding
import com.jarvis.anime.databinding.ItemBookSelectVolumeBinding
import com.jarvis.anime.extension.ViewExtension.Companion.addClick
import com.jarvis.anime.model.BaseChapter
import com.jarvis.anime.model.Volume
import com.jarvis.anime.model.chapter.Chapter
import com.jarvis.anime.model.mangaChapter.MangaChapter

class BookVolumeChapterAdapter(context: Context, val onChapterClick: (chapter: BaseChapter) -> Unit) : BaseRevampRecyclerViewAdapter(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_VOLUME -> {
                val mViewDataBinding = ItemBookSelectVolumeBinding.inflate(
                    LayoutInflater.from(context), parent, false)
                VolumeViewHolder(mViewDataBinding)
            }
            TYPE_CHAPTER -> {
                val mViewDataBinding = ItemBookSelectChapterBinding.inflate(
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

    inner class VolumeViewHolder(val binding: ItemBookSelectVolumeBinding) : BaseRevampViewHolder<Volume>(binding.root) {
        override fun onBind(position: Int, item: Volume) {
            binding.setVariable(BR.volume, item)
            binding.executePendingBindings()
        }
    }

    inner class ChapterViewHolder(val binding: ItemBookSelectChapterBinding) : BaseRevampViewHolder<BaseChapter>(binding.root) {
        override fun onBind(position: Int, item: BaseChapter) {
            val title = if (item is Chapter) {
                item.getNameForLocale()
            } else if (item is MangaChapter) {
                item.getSectionNameForLocale()
            } else ""
            binding.setVariable(BR.title, title)
            binding.executePendingBindings()
            binding.chipChapter.apply {
                addClick({
                    onChapterClick(item)
                })
                isSelected = item.isRead == true
            }
        }
    }

    companion object {
        private const val TYPE_VOLUME = 0
        private const val TYPE_CHAPTER = 1
    }
}