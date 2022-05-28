package com.jarvis.anime.model.mangaChapter

import com.jarvis.anime.model.BaseChapterOrigin

class MangaChapterUpdateOrigin(mangaChapter: MangaChapter) : BaseChapterOrigin(mangaChapter) {
    var image_id_list: ArrayList<String>? = null

    init {
        image_id_list = mangaChapter.image_id_list
    }
}