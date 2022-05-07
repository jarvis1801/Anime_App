package com.jarvis.acg.repository.mangaChapter

import com.jarvis.acg.base.BaseDataSource
import com.jarvis.acg.network.MangaChapterService
import com.jarvis.acg.network.RetrofitClient

class MangaChapterRemoteDataSource : BaseDataSource() {
    suspend fun getChapterListByIdList(idList: String) = getResult {
        RetrofitClient()
            .getService<MangaChapterService>()
            .getChapterListByIdList(idList)
    }
}