package com.jarvis.acg.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jarvis.acg.config.home.BottomMenuConfig
import com.jarvis.acg.ui.home.section.SettingFragment

class HomeSectionAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {
    var fragmentList: ArrayList<Fragment> = arrayListOf()

    init {
        fragmentList.clear()
        fragmentList.addAll(BottomMenuConfig().getShowFragmentList())
        fragmentList.add(SettingFragment())
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}