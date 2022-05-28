package com.jarvis.anime.network

import com.jarvis.anime.model.Author
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthorService {

    @GET("author/list/{idList}")
    suspend fun getAuthorListByIdList(@Path("idList") idList: String) : Response<ArrayList<Author>>
}