package com.jarvis.anime.model

import com.jarvis.anime.util.LanguageUtil
import com.jarvis.anime.util.LanguageUtil.TYPE_EN
import com.jarvis.anime.util.LanguageUtil.TYPE_JP
import com.jarvis.anime.util.LanguageUtil.TYPE_ZH

data class Translation(
    var tc: String? = null,
    var en: String? = null,
    var jp: String? = null,
    var ro: String? = null
) {
    fun getValue(): String {
        val value = when (LanguageUtil.getLocale()) {
            TYPE_EN -> en
            TYPE_ZH -> tc
            TYPE_JP -> jp
            else -> en
        }

        return if (value.isNullOrEmpty()) tc.toString() else value
    }
}