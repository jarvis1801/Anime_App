package com.jarvis.anime.repository.novel

import com.jarvis.anime.base.BaseDataSource
import com.jarvis.anime.network.NovelService
import com.jarvis.anime.network.RetrofitClient

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