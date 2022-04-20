package com.jarvis.acg.model

import java.util.ArrayList

abstract class Book(
    var painter_id_list: ArrayList<String>? = null,
    var publishing_house_id_list: ArrayList<String>? = null,
    var publish_start_date: String? = null,
    var publish_end_date: String? = null
) : BaseACGObject()