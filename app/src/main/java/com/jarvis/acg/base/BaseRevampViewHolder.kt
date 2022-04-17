package com.jarvis.acg.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRevampViewHolder<D>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    open fun onBind(position: Int, item: D) { }
}