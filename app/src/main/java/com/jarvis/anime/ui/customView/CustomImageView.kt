package com.jarvis.anime.ui.customView

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.max
import kotlin.math.min
import kotlin.properties.Delegates

class CustomImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : AppCompatImageView(context, attrs, defStyleAttr) {

    private var isEnableScale = true
    private var mScaleFactor = 1f
    private var originX by Delegates.notNull<Float>()
    private var originY by Delegates.notNull<Float>()
    private var translateX = 0f
    private var translateY = 0f


    private val scaleListener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            mScaleFactor *= detector.scaleFactor

            // Don't let the object get too small or too large.
            mScaleFactor = max(1f, min(mScaleFactor, 5.0f))

            invalidate()
            return true
        }
    }

    private val mScaleDetector = ScaleGestureDetector(context, scaleListener)

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        // Let the ScaleGestureDetector inspect all events.
        return if (isEnableScale) {
            mScaleDetector.onTouchEvent(ev)
            when (ev.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    originX = ev.x
                    originY = ev.y
                }

                MotionEvent.ACTION_MOVE -> {
                    val pointerIndex = ev.findPointerIndex(ev.getPointerId(0));
                    val x = ev.getX(pointerIndex)
                    val y = ev.getY(pointerIndex)
                    val dx = x - originX
                    val dy = y - originY

                    translateX += dx
                    translateY += dy
                    invalidate()
                    originX = x
                    originY = y
                }

                MotionEvent.ACTION_UP -> {
                    originX = 0f
                    originY = 0f
                }
            }
            return true
        } else {
            super.onTouchEvent(ev)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        if (isEnableScale) {
            canvas?.apply {
                save()
                translate(translateX, translateY)
                scale(mScaleFactor, mScaleFactor)
                // onDraw() code goes here
            }
        }

        super.onDraw(canvas)
        canvas?.restore()
    }
}