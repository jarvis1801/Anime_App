package com.jarvis.anime.repository.publishingHouse

import com.jarvis.anime.base.BaseDataSource
import com.jarvis.anime.network.PublishingHouseService
import com.jarvis.anime.network.RetrofitClient

class PublishingHouseRemoteDataSource : BaseDataSource() {

    suspend fun getPublishingHouseListByIdList(idList: String) = getResult {
        RetrofitClient()
            .getService<PublishingHouseService>()
            .getPublishingHouseListByIdList(idList)
    }
}