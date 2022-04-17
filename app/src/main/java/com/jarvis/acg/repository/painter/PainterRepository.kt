package com.jarvis.acg.repository.painter

import com.jarvis.acg.base.BaseRepository
import com.jarvis.acg.extension.Extension.Companion.toArrayList
import com.jarvis.acg.repository.localDataSource.dao.PainterDao

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