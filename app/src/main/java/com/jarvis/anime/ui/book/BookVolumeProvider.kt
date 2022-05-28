package com.jarvis.anime.ui.book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.jarvis.anime.R
import com.jarvis.anime.model.Volume

class BookVolumeProvider: ItemViewBinder<Volume, BookVolumeProvider.ViewHolder>() {


    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_book_select_volume, parent, false))
    }

//    override fun onCreate(context: Context, parent: ViewGroup): ViewHolder {
//        return ViewHolder(ItemBookSelectVolumeBinding.inflate(LayoutInflater.from(context), parent, false))
//    }

    override fun onBindViewHolder(holder: ViewHolder, item: Volume) {
//        holder.onBind(holder.bindingAdapterPosition, item)
    }

    inner class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int, item: Volume) {
//            binding.setVariable(BR.volume, item)
//            binding.executePendingBindings()
        }
    }
}