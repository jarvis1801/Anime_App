package com.jarvis.acg.ui.customView

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.jarvis.acg.R


class CustomTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : AppCompatTextView(context, attrs, defStyleAttr) {

    private var isVerticalText = false

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.CustomTextView, 0, 0).apply {
            try {
                isVerticalText = getBoolean(R.styleable.CustomTextView_isVerticalText, false)
            } finally {
                recycle()
            }
        }
    }
}