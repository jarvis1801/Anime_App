package com.jarvis.acg.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

abstract class BaseViewModel : ViewModel() {
    var requestApiCount = MutableLiveData(0)

    var isRequestLoading = MutableLiveData(false)

    fun requestApi() {
        Log.d("chris", "requestApi")
        requestApiCount.value?.let {
            val count = it + 1
            requestApiCount.postValue(count)
        }
    }

    fun requestApiFinished() {
        Log.d("chris", "requestApiFinished")
        requestApiCount.value?.let {
            var count = it - 1
            if (count < 0) count = 0
            requestApiCount.postValue(count)
        }
    }
}