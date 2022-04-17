package com.jarvis.acg.extension

import android.app.Activity
import android.os.SystemClock
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment

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

        fun Activity.hideStatusBar() {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

            val attrib = window.attributes
            attrib.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES

            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        fun Activity.showStatusBar() {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

        fun Fragment.hideStatusBar() { requireActivity().hideStatusBar() }

        fun Fragment.showStatusBar() { requireActivity().showStatusBar() }
    }
}