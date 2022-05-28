package com.jarvis.anime.model.media

import android.os.Parcelable
import com.jarvis.anime.model.BaseObject

abstract class Media(
    var url: String? = null,
    var order: Int? = null,
    var fileSize: Int? = null
) : BaseObject(), Parcelable