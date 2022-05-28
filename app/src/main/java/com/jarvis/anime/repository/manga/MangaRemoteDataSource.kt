package com.jarvis.anime.repository.manga

import com.jarvis.anime.base.BaseDataSource
import com.jarvis.anime.network.MangaService
import com.jarvis.anime.network.RetrofitClient

class MangaRemoteDataSource : BaseDataSource() {
    suspend fun getMangaList() = getResult {
        RetrofitClient()
            .getService<MangaService>()
            .getMangaList()
    }

    suspend fun getMangaById(id: String) = getResult {
        RetrofitClient()
            .getService<MangaService>()
            .getMangaById(id)
    }
}