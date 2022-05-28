package com.jarvis.anime.model.chapter

import com.jarvis.anime.model.BaseNameObject

class ChapterUpdate(chapter: Chapter) : BaseNameObject(chapter) {
//    var currentLine: Int? = -1
//    var isRead: Boolean? = null

    init {
        id = chapter.id
        created_at = chapter.created_at
        updated_at = chapter.updated_at
//        if (currentLine != -1) {
//            currentLine = chapter.currentLine
//        }
//        if (isRead != null) {
//            isRead = chapter.isRead
//        }
    }
}