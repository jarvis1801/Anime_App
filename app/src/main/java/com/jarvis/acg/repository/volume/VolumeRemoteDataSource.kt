package com.jarvis.acg.repository.volume

import com.jarvis.acg.base.BaseDataSource
import com.jarvis.acg.network.RetrofitClient
import com.jarvis.acg.network.VolumeService

class VolumeRemoteDataSource : BaseDataSource() {
    suspend fun getVolumeListByIdList(idList: String) = getResult {
        RetrofitClient()
            .getService<VolumeService>()
            .getVolumeListByIdList(idList)
    }
}