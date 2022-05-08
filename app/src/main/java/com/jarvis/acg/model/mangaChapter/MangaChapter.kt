package com.jarvis.acg.model.mangaChapter

import androidx.room.Entity
import androidx.room.Ignore
import com.jarvis.acg.model.BaseChapter
import com.jarvis.acg.model.media.Image

@Entity(tableName = "mangaChapter")
data class MangaChapter(
    var image_id_list: ArrayList<String>? = null,


    var lastPosition: Int? = 0,
    @Ignore var imageList: ArrayList<Image>? = null
) : BaseChapter() {
}