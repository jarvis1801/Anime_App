package com.jarvis.acg.repository.volume

import com.jarvis.acg.base.BaseRepository
import com.jarvis.acg.extension.Extension.Companion.join
import com.jarvis.acg.extension.Extension.Companion.toArrayList
import com.jarvis.acg.repository.localDataSource.dao.ChapterDao
import com.jarvis.acg.repository.localDataSource.dao.VolumeDao

class VolumeRepository(
    val remoteDataSource: VolumeRemoteDataSource,
    val volumeDao: VolumeDao
) : BaseRepository() {

    suspend fun getVolumeListByIdList(idList: ArrayList<String>) = performGet(
        databaseQuery = { volumeDao.getByVolumeListByIdList(idList).toArrayList() },
        networkCall = { remoteDataSource.getVolumeListByIdList(idList.join("_")) },
        saveCallResult = { volumeDao.insertAll(it) }
    )

    fun getVolumeListByIdListFromDB(idList: List<String>) = volumeDao.getByVolumeListByIdList(idList)
}