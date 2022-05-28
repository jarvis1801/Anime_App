package com.jarvis.anime.repository.publishingHouse

import com.jarvis.anime.base.BaseRepository
import com.jarvis.anime.extension.Extension.Companion.toArrayList
import com.jarvis.anime.repository.localDataSource.dao.PublishingHouseDao

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