package com.jarvis.acg.model.media

import androidx.room.Entity
import androidx.room.Ignore

@Entity(tableName = "image")
class Image(
    var imageWidth: Int? = null,
    var imageHeight: Int? = null,

    @Ignore var imageString: String? = null
) : Media()