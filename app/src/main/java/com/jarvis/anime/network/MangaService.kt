package com.jarvis.anime.network

import com.jarvis.anime.model.Manga
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MangaService {
    @GET("manga/list")
    suspend fun getMangaList() : Response<ArrayList<Manga>>


    @GET("manga/id")
    suspend fun getMangaById(@Path("id") id: String) : Response<Manga>
}