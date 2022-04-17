package com.jarvis.acg.network

import com.jarvis.acg.model.Chapter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ChapterService {

    @GET("chapter/list/{idList}")
    suspend fun getChapterListByIdList(@Path("idList") idList: String) : Response<ArrayList<Chapter>>
}