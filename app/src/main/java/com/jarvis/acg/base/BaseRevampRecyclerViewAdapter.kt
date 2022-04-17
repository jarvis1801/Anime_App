package com.jarvis.acg.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRevampRecyclerViewAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    protected var dataList = arrayListOf<Any>()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as BaseRevampViewHolder<Any>
        holder.onBind(position, dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    protected fun Int.createView(parent: ViewGroup): View {
        return LayoutInflater.from(context).inflate(this, parent, false)
    }

    protected inline fun <reified T> Int.getCurrentItem(): T {
        return dataList[this] as T
    }

    fun updateData(list: ArrayList<Any>, isNotifyDataChange: Boolean = true) {
        dataList = list
        // todo change diff util
        if (isNotifyDataChange) notifyDataSetChanged()
    }

    fun addAllData(list: ArrayList<Any>, isNotifyDataChange: Boolean = true) {
        dataList.addAll(list)
        // todo change diff util
        if (isNotifyDataChange) notifyDataSetChanged()
    }

    fun addData(data: Any, isNotifyDataChange: Boolean = true) {
        dataList.add(data)
        // todo change diff util
        if (isNotifyDataChange) notifyDataSetChanged()
    }

    fun clearData() {
        dataList.clear()
        notifyDataSetChanged()
    }

    companion object {
        const val TYPE_UNKNOWN = -1
    }
}
