package com.jarvis.acg.model

import com.jarvis.acg.util.LanguageUtil
import com.jarvis.acg.util.LanguageUtil.TYPE_EN
import com.jarvis.acg.util.LanguageUtil.TYPE_JP
import com.jarvis.acg.util.LanguageUtil.TYPE_ZH

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