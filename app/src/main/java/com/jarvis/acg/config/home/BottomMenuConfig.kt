package com.jarvis.acg.config.home

import com.google.gson.Gson
import com.jarvis.acg.Extension.Companion.fromJson
import com.jarvis.acg.base.BaseConfig

class BottomMenuConfig : BaseConfig<List<BottomMenu>>() {

    override var TAG: String = "BottomMenuConfig"

    override fun provideConfig(configStr: String): List<BottomMenu> {
        return Gson().fromJson(configStr)
    }
}