package com.jarvis.acg.repository.manga

import com.jarvis.acg.base.BaseDataSource
import com.jarvis.acg.network.MangaService
import com.jarvis.acg.network.RetrofitClient

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