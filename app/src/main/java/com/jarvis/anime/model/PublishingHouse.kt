package com.jarvis.anime.model

import androidx.room.Entity

@Entity(tableName = "publishingHouse")
data class PublishingHouse(
    var country: String? = null
) : BaseNameObject()