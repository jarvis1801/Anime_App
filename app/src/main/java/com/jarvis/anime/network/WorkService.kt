package com.jarvis.anime.network

import com.jarvis.anime.model.Work
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WorkService {

    @GET("work/list/{idList}")
    suspend fun getWorkListByIdList(@Path("idList") idList: String) : Response<ArrayList<Work>>

    @GET("work/{id}")
    suspend fun getWorkById(@Path("id") id: String) : Response<Work>
}