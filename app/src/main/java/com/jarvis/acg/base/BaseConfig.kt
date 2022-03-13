package com.jarvis.acg.base

import com.google.gson.Gson
import com.jarvis.acg.App
import com.jarvis.acg.util.SharedPreferencesUtil

abstract class BaseConfig<T> {
    abstract var TAG: String

    abstract fun provideConfig(configStr: String) : T

    fun getConfig(): T {
        var configStr = SharedPreferencesUtil().getString(TAG)
        if (configStr.isBlank()) {
            configStr = getConfigAsset()
        }

        return provideConfig(configStr)
    }

    fun saveConfig(config: T) {
        val configStr = Gson().toJson(config)
        SharedPreferencesUtil().editString(TAG, configStr)
    }

    private fun getConfigAsset() : String {
        return App.instance.getStringFromAsset("${TAG}.json")!!
    }
}