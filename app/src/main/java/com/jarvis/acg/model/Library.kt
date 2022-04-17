package com.jarvis.acg.model

import androidx.room.Entity

@Entity(tableName = "library")
data class Library(
    var country: String? = null
) : BaseNameObject()