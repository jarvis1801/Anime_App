package com.jarvis.acg.network

import com.jarvis.acg.model.media.Image
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageService {
    @GET("image/list/{idList}")
    suspend fun getListByIdList(@Path("idList") idList: String) : Response<ArrayList<Image>>
}