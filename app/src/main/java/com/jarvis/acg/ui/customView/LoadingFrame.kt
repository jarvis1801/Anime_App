package com.jarvis.acg.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.jarvis.acg.R

class LoadingFrame @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    private var isLoading = false
    private var requestingCount = 0

    init {
        inflate(getContext(), R.layout.loading_frame, this)
    }

    fun showLoading() {
        requestingCount++
        if (requestingCount > 0 && !isLoading) {
            isLoading = true
            visibility = VISIBLE
        }
    }

    fun hideLoading() {
        requestingCount--
        if (requestingCount < 0) {
            requestingCount = 0
        }
        if (requestingCount <= 0 && isLoading) {
            isLoading = false
            visibility = GONE
        }
    }
}