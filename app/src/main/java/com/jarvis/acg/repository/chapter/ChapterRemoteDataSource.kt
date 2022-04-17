package com.jarvis.acg.repository.chapter

import com.jarvis.acg.base.BaseDataSource
import com.jarvis.acg.network.AuthorService
import com.jarvis.acg.network.ChapterService
import com.jarvis.acg.network.RetrofitClient

class ChapterRemoteDataSource : BaseDataSource() {
    suspend fun getChapterListByIdList(idList: String) = getResult {
        RetrofitClient()
            .getService<ChapterService>()
            .getChapterListByIdList(idList)
    }
}