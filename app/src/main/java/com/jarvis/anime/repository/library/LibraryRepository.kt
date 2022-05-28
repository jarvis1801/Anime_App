package com.jarvis.anime.repository.library

import com.jarvis.anime.base.BaseRepository
import com.jarvis.anime.extension.Extension.Companion.toArrayList
import com.jarvis.anime.repository.localDataSource.dao.LibraryDao

class LibraryRepository(
    val remoteDataSource: LibraryRemoteDataSource,
    val libraryDao: LibraryDao
) : BaseRepository() {

    suspend fun getLibraryListByIdList(idList: String) = performGet(
        databaseQuery = { libraryDao.getAll().toArrayList() },
        networkCall = { remoteDataSource.getLibraryListByIdList(idList) },
        saveCallResult = { libraryDao.insertAll(it) }
    )
}