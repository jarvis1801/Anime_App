package com.jarvis.acg.model

abstract class BaseACGObject(
    var extra_name: Translation? = null,
    var author_id_list: ArrayList<String>? = null,
    var work_id: String? = null,
    var ended: Boolean? = null,
    var volume_id_list: ArrayList<String>? = null,

    var last_volume_id: String? = null,
    var last_chapter_id: String? = null
) : BaseObject()