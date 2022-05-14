package com.jarvis.acg.util

import com.jarvis.acg.util.EncryptedPreferenceDataStore.KEY_CHAPTER_FONT_LINE_SPACE
import com.jarvis.acg.util.EncryptedPreferenceDataStore.KEY_CHAPTER_FONT_SIZE_SCALE
import com.jarvis.acg.util.EncryptedPreferenceDataStore.getFloat
import com.jarvis.acg.util.EncryptedPreferenceDataStore.putFloat

object FontUtil {

    const val FONT_SIZE_MAX_SCALE = 2.0
    const val FONT_SIZE_MIN_SCALE = 0.5

    const val LINE_SPACE_MAX_SIZE = 60.0
    const val LINE_SPACE_MIN_SIZE = 0.9

    fun setChapterFontSizeScale(newValue: Float) {
        if (newValue in FONT_SIZE_MIN_SCALE..FONT_SIZE_MAX_SCALE) {
            KEY_CHAPTER_FONT_SIZE_SCALE.putFloat(newValue)
        }
    }

    fun getChapterFontSizeScale(): Float {
        return KEY_CHAPTER_FONT_SIZE_SCALE.getFloat(1f)
    }

    fun setChapterFontLineSpace(newValue: Float) {
        if (newValue in LINE_SPACE_MIN_SIZE..LINE_SPACE_MAX_SIZE) {
            KEY_CHAPTER_FONT_LINE_SPACE.putFloat(newValue)
        }
    }

    fun getChapterFontLineSpace(): Float {
        return KEY_CHAPTER_FONT_LINE_SPACE.getFloat(1f)
    }
}