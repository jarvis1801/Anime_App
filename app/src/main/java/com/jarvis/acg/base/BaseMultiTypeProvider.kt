package com.jarvis.acg.base

import android.content.Context
import android.view.ViewGroup
import com.drakeet.multitype.ItemViewDelegate
import java.lang.ref.WeakReference

abstract class BaseMultiTypeProvider<T: Any, VH: BaseSingleViewHolder<T>> : ItemViewDelegate<T, VH>() {
    private lateinit var mContext: WeakReference<Context>

    override fun onCreateViewHolder(context: Context, parent: ViewGroup): VH {
        mContext = WeakReference<Context>(context)
        return onCreate(context, parent)
    }

    abstract fun onCreate(context: Context, parent: ViewGroup): VH

    fun getContext(): Context? {
        return mContext.get()
    }
}