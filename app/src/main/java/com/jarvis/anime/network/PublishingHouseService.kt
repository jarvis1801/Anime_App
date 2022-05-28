package com.jarvis.anime.network

import com.jarvis.anime.model.PublishingHouse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PublishingHouseService {

    @GET("publishingHouse/list/{idList}")
    suspend fun getPublishingHouseListByIdList(@Path("idList") idList: String) : Response<ArrayList<PublishingHouse>>
}