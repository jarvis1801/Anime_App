package com.jarvis.acg.model.media

import com.jarvis.acg.model.BaseObject

abstract class Media(
    var url: String? = null,
    var order: Int? = null
) : BaseObject()