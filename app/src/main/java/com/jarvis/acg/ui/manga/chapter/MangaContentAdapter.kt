package com.jarvis.acg.ui.manga.chapter

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.jarvis.acg.base.BaseSingleRecyclerViewAdapter
import com.jarvis.acg.base.BaseSingleViewHolder
import com.jarvis.acg.databinding.ItemMangaContentBinding
import com.jarvis.acg.model.media.Image
import com.jarvis.acg.repository.resource.ResourceRemoteDataSource
import com.jarvis.acg.util.CipherUtil
import com.jarvis.acg.util.DeviceUtil
import com.jarvis.acg.util.GlideUtil.loadImage
import com.jarvis.acg.util.ReadingModeUtil
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MangaContentAdapter(context: Context, val screenWidth: Int, val screenHeight: Int) : BaseSingleRecyclerViewAdapter<ItemMangaContentBinding, MangaContentAdapter.ViewHolder, Image>(context) {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemMangaContentBinding
        get() = ItemMangaContentBinding::inflate

    private var readingMode = ReadingModeUtil().getReadingMode()

    override fun getViewHolderClass(databinding: ItemMangaContentBinding): ViewHolder {
        return ViewHolder(databinding)
    }

    fun setReadingModeType(readingMode: Int) {
        this.readingMode = readingMode
    }

    inner class ViewHolder(val binding: ItemMangaContentBinding) : BaseSingleViewHolder<Image>(binding) {
        override fun onBind(position: Int, item: Image) {
            item.requestLayoutParam(readingMode)
            super.onBind(position, item)

            item.imageString?.let {
                loadImage(it)
            }

        }

        private fun loadImage(imageString: String) {
            binding.imgContent.loadImage(imageString)
        }

        private fun Image.requestLayoutParam(readingMode: Int) {
            when (readingMode) {
                ReadingModeUtil.TYPE_READING_MODE_FLIP_LEFT_RIGHT, ReadingModeUtil.TYPE_READING_MODE_FLIP_TOP_BOTTOM ->
                    updateLayoutParam(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
                ReadingModeUtil.TYPE_READING_MODE_NOT_FLIP_LEFT_RIGHT -> updateLayoutParam(requestWidth(this), FrameLayout.LayoutParams.MATCH_PARENT)
                ReadingModeUtil.TYPE_READING_MODE_NOT_FLIP_TOP_BOTTOM -> updateLayoutParam(
                    FrameLayout.LayoutParams.MATCH_PARENT, requestHeight(this))
            }
        }

        private fun requestHeight(item: Image): Int {
            if (item.imageHeight != null && item.imageWidth != null) {
                val ratio = item.imageHeight!! / item.imageWidth!!.toFloat()

                return (screenWidth * ratio).toInt()
            }
            return FrameLayout.LayoutParams.WRAP_CONTENT
        }

        private fun requestWidth(item: Image): Int {
            if (item.imageWidth != null && item.imageHeight != null) {
                val ratio = item.imageWidth!! / item.imageHeight!!.toFloat()
                return (screenHeight * ratio).toInt()
            }
            return FrameLayout.LayoutParams.WRAP_CONTENT
        }

        private fun updateLayoutParam(widthSpec: Int, heightSpec: Int) {
            val layoutParams = FrameLayout.LayoutParams(widthSpec, heightSpec)
            binding.container.layoutParams = layoutParams
            binding.imgContent.layoutParams = layoutParams

            binding.container.requestLayout()
            binding.imgContent.requestLayout()
        }
    }
}