package com.jarvis.acg.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.jarvis.acg.ui.manga.chapter.MangaContentAdapter
import com.jarvis.acg.util.EncryptedPreferenceDataStore.KEY_READING_MODE
import com.jarvis.acg.util.EncryptedPreferenceDataStore.getInt
import com.jarvis.acg.util.EncryptedPreferenceDataStore.putInt

class ReadingModeUtil {
    companion object {
        const val TYPE_READING_MODE_FLIP_LEFT_RIGHT = 0
        const val TYPE_READING_MODE_FLIP_TOP_BOTTOM = 1
        const val TYPE_READING_MODE_NOT_FLIP_LEFT_RIGHT = 2
        const val TYPE_READING_MODE_NOT_FLIP_TOP_BOTTOM = 3

        val READING_MODE_LIST = arrayListOf(
            "翻頁 左右",
            "翻頁 上下",
            "不翻頁 左右",
            "不翻頁 上下"
        )
    }

    fun getReadingMode(): Int {
        return KEY_READING_MODE.getInt(TYPE_READING_MODE_FLIP_LEFT_RIGHT)
    }

    fun setReadingMode(readingMode: Int) {
        KEY_READING_MODE.putInt(readingMode)
    }

    fun changeType(snapHelper: PagerSnapHelper, linearLayoutManager: LinearLayoutManager, recyclerView: RecyclerView) {
        val readingMode = getReadingMode()
        val orientation = when (readingMode) {
            TYPE_READING_MODE_FLIP_LEFT_RIGHT, TYPE_READING_MODE_NOT_FLIP_LEFT_RIGHT -> {
                LinearLayoutManager.HORIZONTAL
            }
            else -> LinearLayoutManager.VERTICAL
        }

        when (readingMode) {
            TYPE_READING_MODE_FLIP_LEFT_RIGHT -> {
                changeToFlipLeftRight(snapHelper, linearLayoutManager, orientation, recyclerView)
            }
            TYPE_READING_MODE_FLIP_TOP_BOTTOM-> {
                changeToFlipTopBottom(snapHelper, linearLayoutManager, orientation, recyclerView)
            }
            TYPE_READING_MODE_NOT_FLIP_LEFT_RIGHT -> {
                changeToNotFlipLeftRight(snapHelper, linearLayoutManager, orientation)
            }
            TYPE_READING_MODE_NOT_FLIP_TOP_BOTTOM -> {
                changeToNotFlipTopBottom(snapHelper, linearLayoutManager, orientation)
            }
        }
        val adapter = recyclerView.adapter
        if (adapter is MangaContentAdapter) {
            adapter.setReadingModeType(readingMode)
        }
        adapter?.notifyItemRangeChanged(0, adapter.itemCount)
    }

    private fun changeToFlipLeftRight(snapHelper: PagerSnapHelper, linearLayoutManager: LinearLayoutManager, orientation: Int, recyclerView: RecyclerView) {
        changeFlip(snapHelper, recyclerView)
        changeOrientation(linearLayoutManager, orientation)
    }

    private fun changeToFlipTopBottom(snapHelper: PagerSnapHelper, linearLayoutManager: LinearLayoutManager, orientation: Int, recyclerView: RecyclerView) {
        changeFlip(snapHelper, recyclerView)
        changeOrientation(linearLayoutManager, orientation)
    }

    private fun changeToNotFlipLeftRight(snapHelper: PagerSnapHelper, linearLayoutManager: LinearLayoutManager, orientation: Int) {
        changeFlip(snapHelper, null)
        changeOrientation(linearLayoutManager, orientation)
    }

    private fun changeToNotFlipTopBottom(snapHelper: PagerSnapHelper, linearLayoutManager: LinearLayoutManager, orientation: Int) {
        changeFlip(snapHelper, null)
        changeOrientation(linearLayoutManager, orientation)
    }

    private fun changeFlip(snapHelper: PagerSnapHelper, recyclerView: RecyclerView?) {
        snapHelper.attachToRecyclerView(recyclerView)
    }

    private fun changeOrientation(linearLayoutManager: LinearLayoutManager, orientation: Int) {
        linearLayoutManager.orientation = orientation
    }
}