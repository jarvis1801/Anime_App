package com.jarvis.acg.model

import android.view.View
import androidx.navigation.findNavController
import androidx.room.Entity
import androidx.room.Ignore

@Entity(tableName = "work")
data class Work(
    var thumbnail_id_list: ArrayList<String>? = null,
    var tag_id_list: ArrayList<String>? = null,

    // extra
    @Ignore var book_id: String? = null,
    @Ignore var image_byte_list: ArrayList<String>? = null
) : BaseNameObject()