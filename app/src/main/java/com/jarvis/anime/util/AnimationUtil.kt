package com.jarvis.anime.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.View

object AnimationUtil {
    fun View.addAnimatorListenerAdapter(animator: ObjectAnimator, isShow: Boolean) {
        animator.addListener(object: AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                isEnabled = false
                if (isShow) {
                    visibility = View.VISIBLE
                }
            }

            override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                if (!isShow) {
                    visibility = View.GONE
                }
                isEnabled = true
            }
        })
    }
}