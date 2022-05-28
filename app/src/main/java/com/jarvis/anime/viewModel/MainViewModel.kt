package com.jarvis.anime.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jarvis.anime.base.BaseViewModel

class MainViewModel: BaseViewModel() {

    private val _prefetchImageIdList = MutableLiveData<ArrayList<String>>()
    val prefetchImageIdList = _prefetchImageIdList as LiveData<ArrayList<String>>

    fun setPrefetchImageIdList(list: ArrayList<String>) {
        _prefetchImageIdList.postValue(list)
    }
}