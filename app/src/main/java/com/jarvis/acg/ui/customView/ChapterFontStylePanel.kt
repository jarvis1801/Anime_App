package com.jarvis.acg.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.jarvis.acg.databinding.PanelChapterFontStyleBinding
import com.jarvis.acg.util.FontUtil
import com.jarvis.acg.util.FontUtil.FONT_SIZE_MAX_SCALE
import com.jarvis.acg.util.FontUtil.FONT_SIZE_MIN_SCALE
import com.jarvis.acg.util.FontUtil.LINE_SPACE_MAX_SIZE
import com.jarvis.acg.util.FontUtil.LINE_SPACE_MIN_SIZE


class ChapterFontStylePanel @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: PanelChapterFontStyleBinding
    private var currentFontSizeScale = FontUtil.getChapterFontSizeScale()
    private var currentLineSpaceExtra = FontUtil.getChapterFontLineSpace()

    var onUpdateCallback: () -> Unit = {}

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = PanelChapterFontStyleBinding.inflate(inflater, this, true)

        initListener()
    }

    private fun initListener() {
        binding.ivIncreaseFontSizeScale.setOnClickListener {
            val newValue = currentFontSizeScale + 0.1f
            if (newValue <= FONT_SIZE_MAX_SCALE) {
                FontUtil.setChapterFontSizeScale(newValue)
                currentFontSizeScale = newValue
                updateTextView()
            }
        }

        binding.ivDecreaseFontSizeScale.setOnClickListener {
            val newValue = currentFontSizeScale - 0.1f
            if (newValue >= FONT_SIZE_MIN_SCALE) {
                FontUtil.setChapterFontSizeScale(newValue)
                currentFontSizeScale = newValue
                updateTextView()
            }
        }

        binding.ivIncreaseLineSpace.setOnClickListener {
            val newValue = currentLineSpaceExtra + 1f
            if (newValue <= LINE_SPACE_MAX_SIZE) {
                FontUtil.setChapterFontLineSpace(newValue)
                currentLineSpaceExtra = newValue
                updateTextView()
            }
        }

        binding.ivDecreaseLineSpace.setOnClickListener {
            val newValue = currentLineSpaceExtra - 1f
            if (newValue >= LINE_SPACE_MIN_SIZE) {
                FontUtil.setChapterFontLineSpace(newValue)
                currentLineSpaceExtra = newValue
                updateTextView()
            }
        }
    }

    private fun updateTextView() {
        onUpdateCallback()
    }
}