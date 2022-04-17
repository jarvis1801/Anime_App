package com.jarvis.acg.ui.home

import android.view.View
import androidx.core.view.forEach
import androidx.viewpager2.widget.ViewPager2
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseFragment
import com.jarvis.acg.config.home.BottomMenuConfig
import com.jarvis.acg.databinding.FragmentHomeBinding
import com.jarvis.acg.ui.home.adapter.HomeSectionAdapter
import com.jarvis.acg.viewModel.MainViewModel
import com.jarvis.acg.viewModel.home.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel, MainViewModel>() {

    override fun getLayoutId(): Int = R.layout.fragment_home
    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java
    override fun getActivityViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun initView() {
        setupBottomBar()
        setupViewPager()
    }

    private fun setupBottomBar() {
        val menuConfig = BottomMenuConfig().getConfig()
        val visibleMenuItemIdList = arrayListOf<Int>()
        val deleteMenuIdList = arrayListOf<Int>()
        getDataBinding().bottomNavigation.apply {
            menu.forEach { menuItem ->
                menuConfig.find { (it.id == menuItem.titleCondensed && it.isShow) || it.id == BottomMenuConfig.ID_SETTING }?.let {
                    visibleMenuItemIdList.add(menuItem.itemId)
                } ?: let { deleteMenuIdList.add(menuItem.itemId) }
            }
            deleteMenuIdList.forEach { menu.removeItem(it) }
            setOnItemSelectedListener {
                mViewModel?.setCurrentSectionPageByMenuItemId(it.itemId)
                true
            }
        }
        mViewModel?.setVisibleMenuItemIdList(visibleMenuItemIdList)
    }

    private fun setupViewPager() {
        val adapter = HomeSectionAdapter(childFragmentManager, lifecycle)
        mViewModel?.getVisibleFragmentList()?.let { adapter.fragmentList = it }
        getDataBinding().viewpager.apply {
            setAdapter(adapter)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            getChildAt(0).overScrollMode = View.OVER_SCROLL_NEVER
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    mViewModel?.setCurrentSectionPage(position)
                }
            })
            requestDisallowInterceptTouchEvent(false)
        }
    }

    override fun initListener() {
        // observe page change
        mViewModel?.getCurrentSectionPage()?.observe(this) { page ->
            getDataBinding().bottomNavigation.menu.getItem(page).isChecked = true
            getDataBinding().viewpager.currentItem = page
        }
    }

    override fun initStartEvent() {
        
    }

}