package com.jarvis.acg.network

import com.jarvis.acg.model.Manga
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MangaService {
    @GET("manga/list")
    suspend fun getMangaList() : Response<ArrayList<Manga>>


    @GET("manga/id")
    suspend fun getMangaById(@Path("id") id: String) : Response<Manga>
}