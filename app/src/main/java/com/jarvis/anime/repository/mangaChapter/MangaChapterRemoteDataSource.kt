package com.jarvis.anime.repository.mangaChapter

import com.jarvis.anime.base.BaseDataSource
import com.jarvis.anime.network.MangaChapterService
import com.jarvis.anime.network.RetrofitClient

class MangaChapterRemoteDataSource : BaseDataSource() {
    suspend fun getChapterListByIdList(idList: String) = getResult {
        RetrofitClient()
            .getService<MangaChapterService>()
            .getChapterListByIdList(idList)
    }
}