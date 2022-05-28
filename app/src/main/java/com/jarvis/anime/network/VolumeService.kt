package com.jarvis.anime.network

import com.jarvis.anime.model.Volume
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface VolumeService {

    @GET("volume/list/{idList}")
    suspend fun getVolumeListByIdList(@Path("idList") idList: String) : Response<ArrayList<Volume>>
}