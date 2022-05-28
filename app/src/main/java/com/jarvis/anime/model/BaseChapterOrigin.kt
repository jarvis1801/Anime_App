package com.jarvis.anime.model

abstract class BaseChapterOrigin(
    var sectionName: Translation? = null,
    var volume_id: String? = null,
    var order: Int? = null,


) : BaseNameObject() {

    constructor(baseChapter: BaseChapter) : this(
        sectionName = baseChapter.sectionName,
        volume_id = baseChapter.volume_id,
        order = baseChapter.order
    )
}