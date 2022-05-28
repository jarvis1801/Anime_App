package com.jarvis.anime.util.home

import androidx.fragment.app.Fragment
import com.jarvis.anime.R
import com.jarvis.anime.ui.home.section.AnimeSectionFragment
import com.jarvis.anime.ui.home.section.MangaSectionFragment
import com.jarvis.anime.ui.home.section.NovelSectionFragment
import com.jarvis.anime.ui.home.section.SettingFragment

class BottomMenuUtil {

    fun getFragmentByMenuId(menuId: Int) : Fragment? {
        return when (menuId) {
            R.id.navigation_bar_anime -> AnimeSectionFragment()
            R.id.navigation_bar_manga -> MangaSectionFragment()
            R.id.navigation_bar_novel -> NovelSectionFragment()
            R.id.navigation_bar_setting -> SettingFragment()
            else -> null
        }
    }
}