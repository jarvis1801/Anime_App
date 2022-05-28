package com.jarvis.anime.model

import androidx.room.Entity

@Entity(tableName = "volume")
data class Volume(
    var order: Int? = null,
    var book_id: String? = null,
    var sticky_header: Translation? = null,
    var chapter_id_list: ArrayList<String>? = null
) : BaseNameObject()