package com.jarvis.anime.viewModel.home

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jarvis.anime.base.BaseViewModel
import com.jarvis.anime.model.Work
import com.jarvis.anime.util.home.BottomMenuUtil

class HomeViewModel: BaseViewModel() {

    private var _novelWorkList = MutableLiveData<ArrayList<Work>>()
    var novelWorkList = _novelWorkList as LiveData<ArrayList<Work>>

    private val visibleMenuItemIdList = MutableLiveData<ArrayList<Int>>()

    private val currentSectionPage = MutableLiveData<Int>()

    fun getVisibleFragmentList(): ArrayList<Fragment> {
        val fragmentList = arrayListOf<Fragment>()
        val menuItemIdList = visibleMenuItemIdList.value!!
        menuItemIdList.forEach { id ->
            BottomMenuUtil().getFragmentByMenuId(id)?.let { fragmentList.add(it) }
        }

        return fragmentList
    }

    fun setVisibleMenuItemIdList(value: ArrayList<Int>) {
        visibleMenuItemIdList.value = value
    }

    fun getCurrentSectionPage(): LiveData<Int> { return currentSectionPage }

    fun setCurrentSectionPageByMenuItemId(id: Int) {
        visibleMenuItemIdList.value?.indexOf(id)?.let { setCurrentSectionPage(it) }
    }

    fun setCurrentSectionPage(position: Int) {
        currentSectionPage.postValue(position)
    }
}