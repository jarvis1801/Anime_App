package com.jarvis.anime.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ResourceService {

    @GET("imageResource")
    suspend fun getImage(@Query("path") imagePath: String): Response<String>

    @GET("workThumbnail")
    suspend fun getWorkThumbnail(@Query("path") imagePath: String): Response<String>
}