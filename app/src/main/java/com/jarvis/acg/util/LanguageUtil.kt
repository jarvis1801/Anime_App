package com.jarvis.acg.util

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import com.jarvis.acg.util.EncryptedPreferenceDataStore.KEY_I18N_LANG
import com.jarvis.acg.util.EncryptedPreferenceDataStore.getString
import com.jarvis.acg.util.EncryptedPreferenceDataStore.putString
import java.util.*

object LanguageUtil {

    var TYPE_EN = Locale.US.language + "_" + Locale.US.country
    var TYPE_ZH = Locale.TRADITIONAL_CHINESE.language + "_HK"
    var TYPE_JP = Locale.JAPAN.language + "_" + Locale.JAPAN.country

    private var DEFAULT_LANG = TYPE_EN

    val ENTRY_VALUES = arrayListOf(
        TYPE_EN,
        TYPE_ZH,
        TYPE_JP
    )

    fun setLocale(newLocale: String) {
        KEY_I18N_LANG.putString(newLocale)
    }

    fun getLocale(): String {
        return KEY_I18N_LANG.getString(DEFAULT_LANG)
    }

    fun createContextForLangChange(context: Context): Context {
        var newLocale = getLocale()
        if (!ENTRY_VALUES.contains(newLocale)) {
            newLocale = DEFAULT_LANG
        }
        return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N)
            updateResources(context, newLocale)
        else
            updateResourcesLegacy(context, newLocale)
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language.split("_")[0], language.split("_")[1])
        Locale.setDefault(locale)

        val config = context.resources.configuration
        config.setLocale(locale)
        config.setLayoutDirection(locale)

        return context.createConfigurationContext(config)
    }

    @Suppress("DEPRECATION")
    private fun updateResourcesLegacy(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources = context.resources

        val config = resources.configuration
        config.locale = locale
        config.setLayoutDirection(locale)

        resources.updateConfiguration(config, resources.displayMetrics)

        return context
    }
}