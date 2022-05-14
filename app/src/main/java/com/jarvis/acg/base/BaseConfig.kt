package com.jarvis.acg.base

import com.google.gson.Gson
import com.jarvis.acg.App
import com.jarvis.acg.util.EncryptedPreferenceDataStore.getString
import com.jarvis.acg.util.EncryptedPreferenceDataStore.putString

abstract class BaseConfig<T> {
    abstract var TAG: String

    abstract fun provideConfig(configStr: String) : T

    fun getConfig(): T {
        var configStr = TAG.getString("")
        if (configStr.isBlank()) {
            configStr = getConfigAsset()
        }

        return provideConfig(configStr)
    }

    fun saveConfig(config: T) {
        val configStr = Gson().toJson(config)
        TAG.putString(configStr)
    }

    private fun getConfigAsset() : String {
        return App.instance.getStringFromAsset("${TAG}.json")!!
    }
}