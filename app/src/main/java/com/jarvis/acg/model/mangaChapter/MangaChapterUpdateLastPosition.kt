package com.jarvis.acg.model.mangaChapter

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class MangaChapterUpdateLastPosition(id: String, lastPosition: Int? = null) {
    @PrimaryKey
    @SerializedName("_id") var id: String = id
    var lastPosition: Int? = 0

    init {
        lastPosition?.let { this.lastPosition = lastPosition }
    }
}