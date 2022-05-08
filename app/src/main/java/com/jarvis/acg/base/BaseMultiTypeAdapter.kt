package com.jarvis.acg.base

import androidx.recyclerview.widget.DiffUtil
import com.drakeet.multitype.MultiTypeAdapter
import com.jarvis.acg.model.BaseObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class BaseMultiTypeAdapter : MultiTypeAdapter() {
    suspend fun submitList(newData: ArrayList<Any>) = coroutineScope {
        val result = withContext(Dispatchers.IO) {
            val diffCallback = ListDiffCallBack(items.toCollection(ArrayList()), newData)
            DiffUtil.calculateDiff(diffCallback)
        }
        withContext(Dispatchers.Main) {
            items = newData.toList()
            result.dispatchUpdatesTo(this@BaseMultiTypeAdapter)
        }
    }

    fun clearItems() {
        items = arrayListOf()
    }
}

class ListDiffCallBack(private val oldData: ArrayList<Any>, private val newData: ArrayList<Any>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldData.size
    }

    override fun getNewListSize(): Int {
        return newData.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldData = oldData[oldItemPosition]
        val newData = newData[newItemPosition]
        return if (newData is BaseObject && oldData is BaseObject) {
            newData.id == oldData.id
        } else {
            false
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldData = oldData[oldItemPosition]
        val newData = newData[newItemPosition]
        return if (newData is BaseObject && oldData is BaseObject) {
            newData.created_at == oldData.created_at && newData.updated_at == oldData.updated_at
        } else {
            false
        }
    }
}