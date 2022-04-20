package com.jarvis.acg.repository.manga

import com.jarvis.acg.base.BaseRepository
import com.jarvis.acg.extension.Extension.Companion.toArrayList
import com.jarvis.acg.repository.localDataSource.dao.MangaDao
import com.jarvis.acg.repository.localDataSource.dao.NovelDao

class MangaRepository(
    val remoteDataSource: MangaRemoteDataSource,
    val mangaDao: MangaDao
) : BaseRepository() {

    suspend fun getMangaList() = performGet(
        databaseQuery = { mangaDao.getAll().toArrayList() },
        networkCall = { remoteDataSource.getMangaList() },
        saveCallResult = { mangaDao.insertAll(it) }
    )

    fun getMangaByIdFromDB(id: String?) = mangaDao.getById(id)
}