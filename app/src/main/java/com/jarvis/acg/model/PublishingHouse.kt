package com.jarvis.acg.model

import androidx.room.Entity

@Entity(tableName = "publishingHouse")
data class PublishingHouse(
    var country: String? = null
) : BaseNameObject()