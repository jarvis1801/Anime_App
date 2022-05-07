package com.jarvis.acg.ui.manga.chapter

import android.R.attr.data
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jarvis.acg.base.BaseSingleRecyclerViewAdapter
import com.jarvis.acg.base.BaseSingleViewHolder
import com.jarvis.acg.databinding.ItemMangaContentBinding
import com.jarvis.acg.model.media.Image
import com.jarvis.acg.repository.resource.ResourceRemoteDataSource
import com.jarvis.acg.util.CipherUtil
import com.jarvis.acg.util.DeviceUtil
import com.jarvis.acg.util.ReadingModeUtil
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.ByteArrayInputStream
import kotlin.text.Charsets.UTF_8


class MangaContentAdapter(context: Context) : BaseSingleRecyclerViewAdapter<ItemMangaContentBinding, MangaContentAdapter.ViewHolder, Image>(context) {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemMangaContentBinding
        get() = ItemMangaContentBinding::inflate

    override fun getViewHolderClass(databinding: ItemMangaContentBinding): ViewHolder {
        return ViewHolder(databinding)
    }

    inner class ViewHolder(val binding: ItemMangaContentBinding) : BaseSingleViewHolder<Image>(binding) {
        override fun onBind(position: Int, item: Image) {
            super.onBind(position, item)

            item.requestLayoutParam(ReadingModeUtil().getReadingMode(context))
            item.url?.let {
                GlobalScope.launch(Main) {
                    if (item.imageString == null) {
                        val imageRequest = async(IO) { ResourceRemoteDataSource().getImageResource(item.url!!) }
                        val imageString = imageRequest.await().data
                        item.imageString = imageString
                    }

                    item.imageString?.let {
                        val imageDecodeByte = CipherUtil.decode(item.imageString!!)

                        Glide.with(context)
                            .asDrawable()
                            .load(imageDecodeByte)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.imgContent)
                    }
                }
            }
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
                val screenWidth = DeviceUtil.getScreenWidth()
                val ratio = item.imageHeight!! / item.imageWidth!!.toFloat()

                return (screenWidth * ratio).toInt()
            }
            return FrameLayout.LayoutParams.WRAP_CONTENT
        }

        private fun requestWidth(item: Image): Int {
            if (item.imageWidth != null && item.imageHeight != null) {
                val screenHeight = DeviceUtil.getScreenWidth()
                val ratio = item.imageWidth!! / item.imageHeight!!.toFloat()
                return (screenHeight * ratio).toInt()
            }
            return FrameLayout.LayoutParams.WRAP_CONTENT
        }

        private fun updateLayoutParam(widthSpec: Int, heightSpec: Int) {
            val layoutParams = FrameLayout.LayoutParams(widthSpec, heightSpec)
            binding.imgContent.layoutParams = layoutParams
            binding.imgContent.requestLayout()
        }
    }
}