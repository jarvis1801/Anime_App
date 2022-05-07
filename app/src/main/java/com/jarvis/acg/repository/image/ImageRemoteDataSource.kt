package com.jarvis.acg.repository.image

import com.jarvis.acg.base.BaseDataSource
import com.jarvis.acg.network.ImageService
import com.jarvis.acg.network.RetrofitClient

class ImageRemoteDataSource : BaseDataSource() {
    suspend fun getListByIdList(idList: String) = getResult {
        RetrofitClient()
            .getService<ImageService>()
            .getListByIdList(idList)
    }
}