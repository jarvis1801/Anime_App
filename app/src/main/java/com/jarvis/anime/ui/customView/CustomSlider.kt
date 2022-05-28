package com.jarvis.anime.ui.customView

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.google.android.material.slider.Slider

class CustomSlider @JvmOverloads constructor(context: Context, attrSets: AttributeSet?= null, defStyle: Int = 0): Slider(context, attrSets, defStyle) {

    var totalCount = 0
    var onChangeListener: OnChangeListener? = null

    fun setOnChangeListener(callback: (newPage: Int) -> Unit) {
        onChangeListener = OnChangeListener { slider, value, fromUser ->
            val page = value.toInt()
            callback(page)
        }
        apply {
            valueTo = totalCount.minus(1).toFloat()
            addOnSliderTouchListener(object : OnSliderTouchListener {
                @SuppressLint("RestrictedApi")
                override fun onStartTrackingTouch(slider: Slider) {
                    addOnChangeListener(onChangeListener!!)
                }

                @SuppressLint("RestrictedApi")
                override fun onStopTrackingTouch(slider: Slider) {
                    removeOnChangeListener(onChangeListener!!)
                }
            })
            addOnChangeListener(onChangeListener!!)
        }
    }
}