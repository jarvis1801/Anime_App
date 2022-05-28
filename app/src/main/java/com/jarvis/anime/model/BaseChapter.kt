package com.jarvis.anime.model

abstract class BaseChapter(
    var sectionName: Translation? = null,
    var volume_id: String? = null,
    var order: Int? = null,


    var isRead: Boolean? = false,
) : BaseNameObject() {

    constructor(baseChapter: BaseChapter) : this(
        sectionName = baseChapter.sectionName,
        volume_id = baseChapter.volume_id,
        order = baseChapter.order,
        isRead = baseChapter.isRead
    )

    fun getSectionNameForLocale(): String { return sectionName?.getValue() ?: "" }
}