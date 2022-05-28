package com.jarvis.anime.repository.chapter

import com.jarvis.anime.base.BaseDataSource
import com.jarvis.anime.network.ChapterService
import com.jarvis.anime.network.RetrofitClient

class ChapterRemoteDataSource : BaseDataSource() {
    suspend fun getChapterListByIdList(idList: String) = getResult {
        RetrofitClient()
            .getService<ChapterService>()
            .getChapterListByIdList(idList)
    }
}