package com.jarvis.acg.repository.novel

import com.jarvis.acg.base.BaseRepository
import com.jarvis.acg.extension.Extension.Companion.toArrayList
import com.jarvis.acg.repository.localDataSource.dao.NovelDao

class NovelRepository(
    val remoteDataSource: NovelRemoteDataSource,
    val novelDao: NovelDao
) : BaseRepository() {

    suspend fun getNovelList() = performGet(
        databaseQuery = { novelDao.getAll().toArrayList() },
        networkCall = { remoteDataSource.getNovelList() },
        saveCallResult = { novelDao.insertAll(it) }
    )

    fun getNovelByIdFromDB(id: String?) = novelDao.getById(id)
}