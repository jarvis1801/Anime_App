package com.jarvis.anime.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

abstract class BaseObject(
    @PrimaryKey @SerializedName("_id") var id: String = "",
    var created_at: String? = null,
    var updated_at: String? = null
) {
    constructor(baseObject: BaseObject) : this(
        id = baseObject.id,
        created_at = baseObject.created_at,
        updated_at = baseObject.updated_at
    )
}