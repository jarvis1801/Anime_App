package com.jarvis.acg.network

import com.jarvis.acg.model.Author
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthorService {

    @GET("author/list/{idList}")
    suspend fun getAuthorListByIdList(@Path("idList") idList: String) : Response<ArrayList<Author>>
}