package com.jarvis.anime.util

import android.app.Activity
import android.content.res.Resources
import android.graphics.Insets
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import android.view.WindowMetrics
import androidx.fragment.app.Fragment


object DeviceUtil {

    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    fun Activity.getScreenHeightIncludeStatusBar(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val metrics: WindowMetrics = windowManager.currentWindowMetrics
            // Gets all excluding insets
            val windowInsets = metrics.windowInsets
            val insets: Insets = windowInsets.getInsetsIgnoringVisibility(
                WindowInsets.Type.navigationBars()
                        or WindowInsets.Type.displayCutout()
            )
            insets.top + insets.bottom + getScreenHeight()
        } else {
            windowManager.defaultDisplay.height
        }
    }

    fun Fragment.getScreenHeightIncludeStatusBar(): Int {
        val windowManager = requireActivity().windowManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val metrics: WindowMetrics = windowManager.currentWindowMetrics
            // Gets all excluding insets
            val windowInsets = metrics.windowInsets
            val insets: Insets = windowInsets.getInsetsIgnoringVisibility(
                WindowInsets.Type.navigationBars()
                        or WindowInsets.Type.displayCutout()
            )
            insets.top + insets.bottom + getScreenHeight()
        } else {
            windowManager.defaultDisplay.height
        }
    }


    fun dpToPx() {

    }

    fun pxToDp() {

    }

    fun pxToDp(dp: Float): Float {
        val metrics: DisplayMetrics = Resources.getSystem().displayMetrics
        return dp * (metrics.densityDpi / 160f)
    }
}