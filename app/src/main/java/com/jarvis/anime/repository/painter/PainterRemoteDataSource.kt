package com.jarvis.anime.repository.painter

import com.jarvis.anime.base.BaseDataSource
import com.jarvis.anime.network.PainterService
import com.jarvis.anime.network.RetrofitClient

class PainterRemoteDataSource : BaseDataSource() {

    suspend fun getPainterListByIdList(idList: String) = getResult {
        RetrofitClient()
            .getService<PainterService>()
            .getPainterListByIdList(idList)
    }
}