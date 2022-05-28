package com.jarvis.anime.repository.resource

import com.jarvis.anime.base.BaseDataSource
import com.jarvis.anime.network.ResourceService
import com.jarvis.anime.network.RetrofitClient

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