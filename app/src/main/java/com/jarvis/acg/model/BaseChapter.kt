package com.jarvis.acg.model

import androidx.room.Ignore

abstract class BaseChapter(
    var sectionName: Translation? = null,
    var volume_id: String? = null,
    var order: Int? = null,
    var isRead: Boolean? = false,
) : BaseNameObject() {
    fun getSectionNameForLocale(): String { return sectionName?.getValue() ?: "" }
}