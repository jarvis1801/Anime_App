package com.jarvis.acg.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jarvis.acg.base.BaseViewModel

class MainViewModel: BaseViewModel() {

    private val _prefetchImageIdList = MutableLiveData<ArrayList<String>>()
    val prefetchImageIdList = _prefetchImageIdList as LiveData<ArrayList<String>>

    fun setPrefetchImageIdList(list: ArrayList<String>) {
        _prefetchImageIdList.postValue(list)
    }
}