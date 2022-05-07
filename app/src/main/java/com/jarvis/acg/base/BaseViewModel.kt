package com.jarvis.acg.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

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