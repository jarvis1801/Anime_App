package com.jarvis.anime.network

import com.jarvis.anime.model.Novel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NovelService {

    @GET("novel/list")
    suspend fun getNovelList() : Response<ArrayList<Novel>>


    @GET("novel/id")
    suspend fun getNovelById(@Path("id") id: String) : Response<Novel>
}