package com.jarvis.anime.repository.volume

import com.jarvis.anime.base.BaseDataSource
import com.jarvis.anime.network.RetrofitClient
import com.jarvis.anime.network.VolumeService

class VolumeRemoteDataSource : BaseDataSource() {
    suspend fun getVolumeListByIdList(idList: String) = getResult {
        RetrofitClient()
            .getService<VolumeService>()
            .getVolumeListByIdList(idList)
    }
}