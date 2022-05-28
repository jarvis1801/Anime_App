package com.jarvis.anime.network

import com.jarvis.anime.model.mangaChapter.MangaChapter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MangaChapterService {

    @GET("mangaChapter/list/{idList}")
    suspend fun getChapterListByIdList(@Path("idList") idList: String) : Response<ArrayList<MangaChapter>>
}