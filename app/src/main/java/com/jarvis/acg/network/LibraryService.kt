package com.jarvis.acg.network

import com.jarvis.acg.model.Library
import com.jarvis.acg.model.Novel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LibraryService {

    @GET("library/list/{idList}")
    suspend fun getLibraryListByIdList(@Path("idList") idList: String) : Response<ArrayList<Library>>
}