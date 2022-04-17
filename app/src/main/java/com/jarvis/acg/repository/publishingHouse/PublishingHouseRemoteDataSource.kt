package com.jarvis.acg.repository.publishingHouse

import com.jarvis.acg.base.BaseDataSource
import com.jarvis.acg.network.PublishingHouseService
import com.jarvis.acg.network.RetrofitClient

class PublishingHouseRemoteDataSource : BaseDataSource() {

    suspend fun getPublishingHouseListByIdList(idList: String) = getResult {
        RetrofitClient()
            .getService<PublishingHouseService>()
            .getPublishingHouseListByIdList(idList)
    }
}