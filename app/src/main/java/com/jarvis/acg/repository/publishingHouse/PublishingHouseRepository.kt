package com.jarvis.acg.repository.publishingHouse

import com.jarvis.acg.base.BaseRepository
import com.jarvis.acg.extension.Extension.Companion.toArrayList
import com.jarvis.acg.repository.localDataSource.dao.PublishingHouseDao

class PublishingHouseRepository(
    val remoteDataSource: PublishingHouseRemoteDataSource,
    val publishingHouseDao: PublishingHouseDao
) : BaseRepository() {

    suspend fun getPublishingHouseListByIdList(idList: String) = performGet(
        databaseQuery = { publishingHouseDao.getAll().toArrayList() },
        networkCall = { remoteDataSource.getPublishingHouseListByIdList(idList) },
        saveCallResult = { publishingHouseDao.insertAll(it) }
    )
}