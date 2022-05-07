package com.jarvis.acg.model.mangaChapter

import com.jarvis.acg.model.BaseNameObject

class MangaChapterUpdate(chapter: MangaChapter) : BaseNameObject(chapter) {
    var image_id_list: ArrayList<String>? = null
    var isRead: Boolean? = false

    init {
        id = chapter.id
        created_at = chapter.created_at
        updated_at = chapter.updated_at
        image_id_list = chapter.image_id_list
        isRead = chapter.isRead
    }
}