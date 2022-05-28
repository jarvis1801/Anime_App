package com.jarvis.anime.network

import com.jarvis.anime.model.media.Image
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageService {
    @GET("image/list/{idList}")
    suspend fun getListByIdList(@Path("idList") idList: String) : Response<ArrayList<Image>>
}