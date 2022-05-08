package com.jarvis.acg.model.mangaChapter

import com.jarvis.acg.model.BaseChapterOrigin

class MangaChapterUpdateOrigin(mangaChapter: MangaChapter) : BaseChapterOrigin(mangaChapter) {
    var image_id_list: ArrayList<String>? = null

    init {
        image_id_list = mangaChapter.image_id_list
    }
}