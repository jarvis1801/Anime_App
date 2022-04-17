package com.jarvis.acg.repository.painter

import com.jarvis.acg.base.BaseDataSource
import com.jarvis.acg.network.PainterService
import com.jarvis.acg.network.RetrofitClient

class PainterRemoteDataSource : BaseDataSource() {

    suspend fun getPainterListByIdList(idList: String) = getResult {
        RetrofitClient()
            .getService<PainterService>()
            .getPainterListByIdList(idList)
    }
}