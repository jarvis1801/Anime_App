package com.jarvis.acg.repository.resource

import com.jarvis.acg.base.BaseDataSource
import com.jarvis.acg.network.PublishingHouseService
import com.jarvis.acg.network.ResourceService
import com.jarvis.acg.network.RetrofitClient
import retrofit2.Response

class ResourceRemoteDataSource : BaseDataSource() {

    suspend fun getImageResource(imagePath: String) = getResult {
        RetrofitClient()
            .getGitHubRetrofitClient().create(ResourceService::class.java)
            .getImage(imagePath)
    }

    suspend fun getWorkThumbnail(imagePath: String) = getResult {
        RetrofitClient()
            .getGitHubRetrofitClient().create(ResourceService::class.java)
            .getWorkThumbnail(imagePath)
    }
}