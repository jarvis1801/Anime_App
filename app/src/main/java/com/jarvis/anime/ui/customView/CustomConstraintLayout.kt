package com.jarvis.anime.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.jarvis.anime.R
import com.jarvis.anime.util.DeviceUtil
import kotlin.properties.Delegates

class CustomConstraintLayout @JvmOverloads constructor(context: Context, attrSets: AttributeSet?= null, defStyle: Int = 0): ConstraintLayout(context, attrSets, defStyle) {
    var preventSendTouchToChild = false
    private var originX by Delegates.notNull<Float>()
    private var originY by Delegates.notNull<Float>()

    companion object {
        private const val BIGGEST_DIFF_Y_VALUE = 0.5f
        private const val BIGGEST_DIFF_X_VALUE = 0.5f
    }

    private val preventClickByChildIdList = arrayListOf(R.id.btn_nearby, R.id.fl_bottom_panel_container)

    var onClickLeft: () -> Unit = {}
    var onClickCenter: () -> Unit = {}
    var onClickRight: () -> Unit = {}

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                originX = ev.x
                originY = ev.y
                preventSendTouchToChild = false
                super.onInterceptTouchEvent(ev)
            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                if (isPerformClick(ev)) {
                    onTouchEvent(ev)
                }
                preventSendTouchToChild = false
                super.onInterceptTouchEvent(ev)
            }
            MotionEvent.ACTION_MOVE -> {
                if (preventSendTouchToChild) {
                    true
                } else {
                    super.onInterceptTouchEvent(ev)
                }
            }
            else -> {
                super.onInterceptTouchEvent(ev)
            }

        }
    }

    private fun isInterceptChildTouch(ev: MotionEvent): Boolean {
        val xDiff: Float = calculateDistance(originX, ev.x)
        val yDiff: Float = calculateDistance(originY, ev.y)

        return yDiff < BIGGEST_DIFF_Y_VALUE || xDiff < BIGGEST_DIFF_X_VALUE
    }

    private fun isPerformClick(ev: MotionEvent): Boolean {
        val xDiff: Float = calculateDistance(originX, ev.x)
        val yDiff: Float = calculateDistance(originY, ev.y)

        children.iterator().forEach { view ->
            if (preventClickByChildIdList.contains(view.id)) {
                if (ev.x.toInt() in view.x.toInt() until view.x.toInt() + view.width &&
                    ev.y.toInt() in view.y.toInt() until view.y.toInt() + view.height) {
                    preventSendTouchToChild = true
                }
            }
        }

        return yDiff < BIGGEST_DIFF_Y_VALUE && xDiff < BIGGEST_DIFF_X_VALUE && !preventSendTouchToChild
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            val screenWidth = DeviceUtil.getScreenWidth()
            when (event.x) {
                in 0f..screenWidth / 3f -> onClickLeft()
                in screenWidth / 3f..screenWidth / 3f * 2 -> onClickCenter()
                in screenWidth / 3f * 2..screenWidth.toFloat() -> onClickRight()
            }
        }
        return super.onTouchEvent(event)
    }

    private fun calculateDistance(origin: Float, new: Float): Float {
        var diff = origin.minus(new)
        if (diff < 0) {
            diff = -diff
        }

        return diff
    }
}