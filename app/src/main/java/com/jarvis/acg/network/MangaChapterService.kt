package com.jarvis.acg.network

import com.jarvis.acg.model.mangaChapter.MangaChapter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MangaChapterService {

    @GET("mangaChapter/list/{idList}")
    suspend fun getChapterListByIdList(@Path("idList") idList: String) : Response<ArrayList<MangaChapter>>
}