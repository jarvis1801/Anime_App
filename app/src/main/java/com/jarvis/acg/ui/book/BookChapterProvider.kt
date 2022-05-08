package com.jarvis.acg.ui.book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.jarvis.acg.R
import com.jarvis.acg.model.BaseChapter

class BookChapterProvider(val onChapterClick: (chapter: BaseChapter) -> Unit) : ItemViewBinder<BaseChapter, BookChapterProvider.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_book_select_chapter, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: BaseChapter) {
//        holder.onBind(holder.bindingAdapterPosition, item)
    }

    inner class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(position: Int, item: BaseChapter) {
//            super.onBind(position, item)

//            val title = if (item is Chapter) {
//                item.getNameForLocale()
//            } else if (item is MangaChapter) {
//                item.getSectionNameForLocale()
//            } else ""
//            binding.setVariable(BR.title, title)
//            binding.executePendingBindings()
//            binding.chipChapter.apply {
//                addClick({
//                    onChapterClick(item)
//                })
//                isSelected = item.isRead == true
//            }
        }
    }
}