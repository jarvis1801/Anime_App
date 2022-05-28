package com.jarvis.anime.repository.image

import com.jarvis.anime.base.BaseDataSource
import com.jarvis.anime.network.ImageService
import com.jarvis.anime.network.RetrofitClient

class ImageRemoteDataSource : BaseDataSource() {
    suspend fun getListByIdList(idList: String) = getResult {
        RetrofitClient()
            .getService<ImageService>()
            .getListByIdList(idList)
    }
}