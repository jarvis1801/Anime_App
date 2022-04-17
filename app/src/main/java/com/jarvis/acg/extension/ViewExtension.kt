package com.jarvis.acg.extension

import android.os.SystemClock
import android.view.View

class ViewExtension {
    companion object {
        fun View.addClick(action: () -> Unit, debounceTime: Long = 1000L) {
            this.setOnClickListener(object : View.OnClickListener {
                private var lastClickTime: Long = 0

                override fun onClick(v: View) {
                    if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
                    else action()

                    lastClickTime = SystemClock.elapsedRealtime()
                }
            })
        }
    }
}