package com.jarvis.acg.repository.work

import com.jarvis.acg.base.BaseRepository
import com.jarvis.acg.model.Work
import com.jarvis.acg.repository.localDataSource.dao.WorkDao

class WorkRepository(
    val workRemoteDataSource: WorkRemoteDataSource,
    val workDao: WorkDao
) : BaseRepository() {

    suspend fun getWorkById(id: String) = performGet(
        databaseQuery = { workDao.getById(id) ?: Work() },
        networkCall = { workRemoteDataSource.getWorkById(id) },
        saveCallResult = { workDao.insert(it) }
    )

    fun getWorkByIdFromDB(id: String?) = workDao.getById(id)
}