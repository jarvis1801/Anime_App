package com.jarvis.acg.model

import androidx.room.Entity
import java.util.*

@Entity(tableName = "novel")
data class Novel(
    var library_id_list: ArrayList<String>? = null,
) : Book()