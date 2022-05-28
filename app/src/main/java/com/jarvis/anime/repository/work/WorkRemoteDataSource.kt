package com.jarvis.anime.repository.work

import com.jarvis.anime.base.BaseDataSource
import com.jarvis.anime.network.RetrofitClient
import com.jarvis.anime.network.WorkService

class WorkRemoteDataSource : BaseDataSource() {

    suspend fun getWorkById(idList: String) = getResult {
        RetrofitClient()
            .getService<WorkService>()
            .getWorkById(idList)
    }
}