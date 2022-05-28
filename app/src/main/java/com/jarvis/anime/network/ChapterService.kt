package com.jarvis.anime.network

import com.jarvis.anime.model.chapter.Chapter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ChapterService {

    @GET("chapter/list/{idList}")
    suspend fun getChapterListByIdList(@Path("idList") idList: String) : Response<ArrayList<Chapter>>
}