package com.jarvis.acg.model.chapter

import com.jarvis.acg.model.BaseNameObject

class ChapterUpdate(chapter: Chapter) : BaseNameObject(chapter) {
    var currentLine: Int? = 0
    var isRead: Boolean? = false

    init {
        id = chapter.id
        created_at = chapter.created_at
        updated_at = chapter.updated_at
        currentLine = chapter.currentLine
        isRead = chapter.isRead
    }
}