package com.jarvis.acg.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceDataStore
import androidx.preference.PreferenceManager
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.jarvis.acg.App

object EncryptedPreferenceDataStore : PreferenceDataStore() {

    const val KEY_READING_MODE = "KEY_READING_MODE"
    const val KEY_DARK_MODE = "KEY_DARK_MODE"
    const val KEY_API_MODE = "KEY_API_MODE"
    const val KEY_I18N_LANG = "KEY_I18N_LANG"
    const val KEY_CHAPTER_FONT_SIZE_SCALE = "KEY_CHAPTER_FONT_SIZE_SCALE"
    const val KEY_CHAPTER_FONT_LINE_SPACE = "KEY_CHAPTER_FONT_LINE_SPACE"

    private const val SHARED_PREFERENCES_NAME = "secret_shared_prefs"

    private var mSharedPreferences: SharedPreferences = try {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        EncryptedSharedPreferences.create(
            masterKeyAlias,
            SHARED_PREFERENCES_NAME,
            App.instance.applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    } catch (e: Exception) {
        PreferenceManager.getDefaultSharedPreferences(App.instance.applicationContext)
    }

    override fun putString(key: String, value: String?) {
        mSharedPreferences.edit().putString(key, value).apply()
    }

    override fun putStringSet(key: String, values: Set<String>?) {
        mSharedPreferences.edit().putStringSet(key, values).apply()
    }

    override fun putInt(key: String, value: Int) {
        mSharedPreferences.edit().putInt(key, value).apply()
    }

    override fun putLong(key: String, value: Long) {
        mSharedPreferences.edit().putLong(key, value).apply()
    }

    override fun putFloat(key: String, value: Float) {
        mSharedPreferences.edit().putFloat(key, value).apply()
    }

    override fun putBoolean(key: String, value: Boolean) {
        mSharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun getString(key: String, defValue: String?): String {
        return mSharedPreferences.getString(key, defValue) ?: ""
    }

    override fun getStringSet(key: String, defValues: Set<String>?): Set<String>? {
        return mSharedPreferences.getStringSet(key, defValues)
    }

    override fun getInt(key: String, defValue: Int): Int {
        return mSharedPreferences.getInt(key, defValue)
    }

    override fun getLong(key: String, defValue: Long): Long {
        return mSharedPreferences.getLong(key, defValue)
    }

    override fun getFloat(key: String, defValue: Float): Float {
        return mSharedPreferences.getFloat(key, defValue)
    }

    override fun getBoolean(key: String, defValue: Boolean): Boolean {
        return mSharedPreferences.getBoolean(key, defValue)
    }

    @JvmName("putString1")
    fun String.putString(value: String?) {
        putString(this, value)
    }

    @JvmName("putStringSet1")
    fun String.putStringSet(values: Set<String>?) {
        putStringSet(this, values)
    }

    @JvmName("putInt1")
    fun String.putInt(value: Int) {
        putInt(this, value)
    }

    @JvmName("putLong1")
    fun String.putLong(value: Long) {
        putLong(this, value)
    }

    @JvmName("putFloat1")
    fun String.putFloat(value: Float) {
        putFloat(this, value)
    }

    @JvmName("putBoolean1")
    fun String.putBoolean(value: Boolean) {
        putBoolean(this, value)
    }

    @JvmName("getString1")
    fun String.getString(defValue: String?): String {
        return getString(this, defValue)
    }

    @JvmName("getStringSet1")
    fun String.getStringSet(defValues: Set<String>?): Set<String>? {
        return getStringSet(this, defValues)
    }

    @JvmName("getInt1")
    fun String.getInt(defValue: Int): Int {
        return getInt(this, defValue)
    }

    @JvmName("getLong1")
    fun String.getLong(defValue: Long): Long {
        return getLong(this, defValue)
    }

    @JvmName("getFloat1")
    fun String.getFloat(defValue: Float): Float {
        return getFloat(this, defValue)
    }

    @JvmName("getBoolean1")
    fun String.getBoolean(defValue: Boolean): Boolean {
        return getBoolean(this, defValue)
    }

    fun isEnableApi(): Boolean {
        return KEY_API_MODE.getBoolean(true)
    }
}