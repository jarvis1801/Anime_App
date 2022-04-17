package com.jarvis.acg.network

import com.jarvis.acg.model.Library
import com.jarvis.acg.model.Painter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PainterService {
    @GET("painter/list/{idList}")
    suspend fun getPainterListByIdList(@Path("idList") idList: String) : Response<ArrayList<Painter>>
}