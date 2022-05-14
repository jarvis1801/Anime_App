package com.jarvis.acg.util

import androidx.appcompat.app.AppCompatDelegate
import com.jarvis.acg.util.EncryptedPreferenceDataStore.KEY_DARK_MODE
import com.jarvis.acg.util.EncryptedPreferenceDataStore.getString
import com.jarvis.acg.util.EncryptedPreferenceDataStore.putString

object DarkModeUtil {

    private const val TYPE_FOLLOW_SYSTEM = "0"
    private const val TYPE_LIGHT_MODE = "1"
    private const val TYPE_DARK_MODE = "2"

    val ENTRY_VALUES = arrayListOf(
        TYPE_FOLLOW_SYSTEM,
        TYPE_LIGHT_MODE,
        TYPE_DARK_MODE
    )

    fun getDarkMode(): String {
        return KEY_DARK_MODE.getString(TYPE_FOLLOW_SYSTEM)
    }

    fun setDarkMode(readingMode: String) {
        KEY_DARK_MODE.putString(readingMode)
        changeDarkMode(readingMode)
    }

//    private fun getDefaultDarkMode(): Boolean {
//        val configuration = App.instance.resources.configuration
//        val currentNightMode = configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
//
//        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
//    }


    fun changeDarkMode(readingMode: String) {
        when (readingMode) {
            TYPE_FOLLOW_SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            TYPE_LIGHT_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            TYPE_DARK_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    fun initDarkModeWhenStart() {
        changeDarkMode(getDarkMode())
    }
}