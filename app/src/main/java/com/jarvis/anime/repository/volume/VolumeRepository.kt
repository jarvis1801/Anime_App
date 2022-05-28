package com.jarvis.anime.repository.volume

import com.jarvis.anime.base.BaseRepository
import com.jarvis.anime.extension.Extension.Companion.join
import com.jarvis.anime.extension.Extension.Companion.toArrayList
import com.jarvis.anime.repository.localDataSource.dao.VolumeDao

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