package com.jarvis.acg.repository.novel

import com.jarvis.acg.base.BaseDataSource
import com.jarvis.acg.network.NovelService
import com.jarvis.acg.network.RetrofitClient

class NovelRemoteDataSource : BaseDataSource() {
    suspend fun getNovelList() = getResult {
        RetrofitClient()
            .getService<NovelService>()
            .getNovelList()
    }

    suspend fun getNovelById(id: String) = getResult {
        RetrofitClient()
            .getService<NovelService>()
            .getNovelById(id)
    }
}