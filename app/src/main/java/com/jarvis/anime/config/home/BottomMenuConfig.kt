package com.jarvis.anime.config.home

import com.google.gson.Gson
import com.jarvis.anime.extension.Extension.Companion.fromJson
import com.jarvis.anime.base.BaseConfig
import com.jarvis.anime.base.BaseFragment
import com.jarvis.anime.extension.Extension.Companion.toArrayList
import com.jarvis.anime.ui.home.section.AnimeSectionFragment
import com.jarvis.anime.ui.home.section.MangaSectionFragment
import com.jarvis.anime.ui.home.section.NovelSectionFragment

class BottomMenuConfig : BaseConfig<List<BottomMenu>>() {

    override var TAG: String = "BottomMenuConfig"

    companion object {
        const val ID_ANIME = "anime"
        const val ID_MANGA = "manga"
        const val ID_NOVEL = "novel"
        const val ID_SETTING = "setting"
    }

    override fun provideConfig(configStr: String): List<BottomMenu> {
        return Gson().fromJson(configStr)
    }

    fun getShowFragmentList() : ArrayList<BaseFragment<*, *, *>> {
        val fragmentList = arrayListOf<BaseFragment<*, *, *>>()
        getConfig().filter { it.isShow }.toArrayList().forEach { menu ->
            val fragment = when (menu.id.lowercase()) {
                ID_ANIME -> AnimeSectionFragment()
                ID_MANGA -> MangaSectionFragment()
                ID_NOVEL -> NovelSectionFragment()
                else -> null
            }
            fragment.takeIf { it != null }?.run { fragmentList.add(this) }
        }
        return fragmentList
    }
}