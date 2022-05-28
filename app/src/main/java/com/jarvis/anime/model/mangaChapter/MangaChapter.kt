package com.jarvis.anime.model.mangaChapter

import androidx.room.Entity
import androidx.room.Ignore
import com.jarvis.anime.model.BaseChapter
import com.jarvis.anime.model.media.Image

@Entity(tableName = "mangaChapter")
data class MangaChapter(
    var image_id_list: ArrayList<String>? = null,


    var lastPosition: Int? = 0,
    @Ignore var imageList: ArrayList<Image>? = null
) : BaseChapter() {
}