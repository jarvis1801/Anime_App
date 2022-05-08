package com.jarvis.acg.model.mangaChapter

data class MangaChapterPage(
    var page: Int = 0,
    var scrollMode: Int = -1,
) {
    companion object {
        const val CHAPTER_PAGE_NON_SCROLL = -1
        const val CHAPTER_PAGE_SMOOTH_SCROLL = 1
        const val CHAPTER_PAGE_SCROLL = 2
    }
}