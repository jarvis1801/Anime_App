package com.jarvis.acg.model.media

import android.os.Parcelable
import com.jarvis.acg.model.BaseObject

abstract class Media(
    var url: String? = null,
    var order: Int? = null,
    var fileSize: Int? = null
) : BaseObject(), Parcelable