package com.jarvis.anime.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import com.jarvis.anime.R
import com.jarvis.anime.util.FontUtil


class CustomTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : AppCompatTextView(context, attrs, defStyleAttr) {

    private var isVerticalText = false
    private var isChapterContent = false

    private var defaultTextSize: Float? = null

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.CustomTextView, 0, 0).apply {
            try {
                isVerticalText = getBoolean(R.styleable.CustomTextView_isVerticalText, false)
                isChapterContent = getBoolean(R.styleable.CustomTextView_isChapterContent, false)
            } finally {
                recycle()
            }
        }

        initStyle()
    }

    private fun initStyle() {
        if (isChapterContent) {
            defaultTextSize = textSize
            setTextSize()

            setLineSpacing()
        }
    }

    fun updateFontStyle() {
        setTextSize()
        setLineSpacing()
    }

    private fun setTextSize() {
        val newTextSize = defaultTextSize!! * FontUtil.getChapterFontSizeScale()
        setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSize)
    }

    private fun setLineSpacing() {
        setLineSpacing(FontUtil.getChapterFontLineSpace(), 1f)
    }
}