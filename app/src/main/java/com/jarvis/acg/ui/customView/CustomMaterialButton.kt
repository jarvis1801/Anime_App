package com.jarvis.acg.ui.customView

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton
import com.jarvis.acg.R

class CustomMaterialButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    MaterialButton(context, attrs, defStyleAttr) {


    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.CustomMaterialButton, 0, 0).apply {
            try {

            } finally {
                recycle()
            }
        }
    }
}