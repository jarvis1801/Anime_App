package com.jarvis.acg.model.mangaChapter

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class MangaChapterUpdateIsRead(id: String, isRead: Boolean? = null) {
    @PrimaryKey
    @SerializedName("_id") var id: String = id
    var isRead: Boolean? = null

    init {
        isRead?.let { this.isRead = isRead }
    }
}