package com.jarvis.acg.util

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.jarvis.acg.App


class SharedPreferencesUtil {

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private fun getSharedPreferences(): SharedPreferences = EncryptedSharedPreferences.create(
        "secret_shared_prefs",
        masterKeyAlias,
        App.instance.applicationContext,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun setReadingMode(value: Int) {
        editInt(KEY_READING_MODE, value)
    }

    fun getReadingMode(): Int {
        return getInt(KEY_READING_MODE, 0)
    }

    fun editInt(key: String, value: Int) {
        val sharedPreferences = getSharedPreferences()
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String, defValue: Int = 0): Int {
        val sharedPreferences = getSharedPreferences()
        return sharedPreferences.getInt(key, defValue)
    }

    fun editLong(key: String, value: Long) {
        val sharedPreferences = getSharedPreferences()
        val editor = sharedPreferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun getLong(key: String, defValue: Long = 0L): Long {
        val sharedPreferences = getSharedPreferences()
        return sharedPreferences.getLong(key, defValue)
    }

    fun editString(key: String, value: String) {
        val sharedPreferences = getSharedPreferences()
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String, defValue: String = ""): String {
        val sharedPreferences = getSharedPreferences()
        return sharedPreferences.getString(key, defValue)!!
    }

    fun editBoolean(key: String, value: Boolean) {
        val sharedPreferences = getSharedPreferences()
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String, defValue: Boolean = false): Boolean {
        val sharedPreferences = getSharedPreferences()
        return sharedPreferences.getBoolean(key, defValue)
    }

    companion object {
        const val KEY_READING_MODE = "KEY_READING_MODE"
    }
}