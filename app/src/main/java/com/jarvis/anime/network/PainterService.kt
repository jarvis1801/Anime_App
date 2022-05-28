package com.jarvis.anime.network

import com.jarvis.anime.model.Painter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PainterService {
    @GET("painter/list/{idList}")
    suspend fun getPainterListByIdList(@Path("idList") idList: String) : Response<ArrayList<Painter>>
}