package com.jarvis.acg.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

abstract class BaseObject(
    @PrimaryKey @SerializedName("_id") var id: String = "",
    var created_at: String? = null,
    var updated_at: String? = null
)