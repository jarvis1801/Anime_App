package com.jarvis.acg.util

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

object GlideUtil {

    fun ImageView.loadImage(imageString: String, onSuccess: (() -> Unit)? = null, onFail: (() -> Unit)? = null) {
        val imageDecodeByte = CipherUtil.decode(imageString)

        var glide = Glide.with(context)
            .asBitmap()
            .load(imageDecodeByte)
            .diskCacheStrategy(DiskCacheStrategy.NONE)

        if (onSuccess != null || onFail != null) {
            glide = glide.listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                    if (onFail != null) {
                        onFail()
                    }
                    return true
                }

                override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    if (onSuccess != null) {
                        onSuccess()
                    }
                    return false
                }

            })
        }
        glide.into(this)
    }
}