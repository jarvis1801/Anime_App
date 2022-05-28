package com.jarvis.anime.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerView @JvmOverloads constructor(context: Context, attrSets: AttributeSet?= null, defStyle: Int = 0): RecyclerView(context, attrSets, defStyle) {

    var scrollChangeListener: OnScrollListener? = null

    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        return when (e.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                super.onInterceptTouchEvent(e)
            }
            MotionEvent.ACTION_MOVE -> {
                e.pointerCount < 2
            }
            else -> super.onInterceptTouchEvent(e)
        }
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        return super.onTouchEvent(e)
    }

    fun setScrollChange(
        idleCallback: () -> Unit = {},
        draggingCallback: () -> Unit = {},
        settlingCallback: () -> Unit = {}
    ) {
        if (scrollChangeListener != null) removeOnScrollListener(scrollChangeListener!!)
        scrollChangeListener = object: OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when (newState) {
                    SCROLL_STATE_IDLE -> {
                        idleCallback()
                    }
                    SCROLL_STATE_DRAGGING -> {
                        draggingCallback()
                    }
                    SCROLL_STATE_SETTLING -> {
                        settlingCallback()
                    }
                }
            }
        }
        addOnScrollListener(scrollChangeListener!!)
    }
}