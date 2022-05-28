package com.jarvis.anime.repository.painter

import com.jarvis.anime.base.BaseRepository
import com.jarvis.anime.extension.Extension.Companion.toArrayList
import com.jarvis.anime.repository.localDataSource.dao.PainterDao

class PainterRepository(
    val remoteDataSource: PainterRemoteDataSource,
    val painterDao: PainterDao
) : BaseRepository() {

    suspend fun getPainterListByIdList(idList: String) = performGet(
        databaseQuery = { painterDao.getAll().toArrayList() },
        networkCall = { remoteDataSource.getPainterListByIdList(idList) },
        saveCallResult = { painterDao.insertAll(it) }
    )
}