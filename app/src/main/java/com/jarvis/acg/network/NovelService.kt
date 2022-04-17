package com.jarvis.acg.network

import com.jarvis.acg.model.Novel
import com.jarvis.acg.model.Work
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NovelService {

    @GET("novel/list")
    suspend fun getNovelList() : Response<ArrayList<Novel>>


    @GET("novel/id")
    suspend fun getNovelById(@Path("id") id: String) : Response<Novel>
}