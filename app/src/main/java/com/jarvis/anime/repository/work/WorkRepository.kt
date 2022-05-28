package com.jarvis.anime.repository.work

import com.jarvis.anime.base.BaseRepository
import com.jarvis.anime.model.Work
import com.jarvis.anime.repository.localDataSource.dao.WorkDao

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