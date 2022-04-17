package com.jarvis.acg.model

abstract class BaseACGObject(
    var extra_name: Translation? = null,
    var author_id_list: ArrayList<String>? = null,
    var work_id: String? = null,
    var ended: Boolean? = null,
    var volume_id_list: ArrayList<String>? = null
) : BaseObject()