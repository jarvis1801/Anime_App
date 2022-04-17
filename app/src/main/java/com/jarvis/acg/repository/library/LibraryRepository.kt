package com.jarvis.acg.repository.library

import com.jarvis.acg.base.BaseRepository
import com.jarvis.acg.extension.Extension.Companion.toArrayList
import com.jarvis.acg.repository.localDataSource.dao.LibraryDao

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