package com.jarvis.acg.model

import androidx.room.Entity
import java.util.*

@Entity(tableName = "novel")
data class Novel(
    var painter_id_list: ArrayList<String>? = null,
    var publishing_house_id_list: ArrayList<String>? = null,
    var library_id_list: ArrayList<String>? = null,
    var publish_start_date: String? = null,
    var publish_end_date: String? = null
) : BaseACGObject()