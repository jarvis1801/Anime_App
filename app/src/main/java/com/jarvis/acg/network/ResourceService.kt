package com.jarvis.acg.network

import com.bumptech.glide.load.engine.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ResourceService {

    @GET("imageResource")
    suspend fun getImage(@Query("path") imagePath: String): Response<String>
}