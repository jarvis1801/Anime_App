package com.jarvis.acg.repository.work

import com.jarvis.acg.base.BaseDataSource
import com.jarvis.acg.network.RetrofitClient
import com.jarvis.acg.network.WorkService

class WorkRemoteDataSource : BaseDataSource() {

    suspend fun getWorkById(idList: String) = getResult {
        RetrofitClient()
            .getService<WorkService>()
            .getWorkById(idList)
    }
}