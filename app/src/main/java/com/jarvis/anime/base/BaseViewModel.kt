package com.jarvis.anime.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    var requestLoading = MutableLiveData<Boolean>()
    var reduceLoading = MutableLiveData<Boolean>()

    fun requestApi() {
        requestLoading.postValue(true)
    }

    fun requestApiFinished() {
        reduceLoading.postValue(true)
    }
}