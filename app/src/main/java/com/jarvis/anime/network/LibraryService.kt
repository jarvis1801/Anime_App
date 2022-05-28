package com.jarvis.anime.network

import com.jarvis.anime.model.Library
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LibraryService {

    @GET("library/list/{idList}")
    suspend fun getLibraryListByIdList(@Path("idList") idList: String) : Response<ArrayList<Library>>
}