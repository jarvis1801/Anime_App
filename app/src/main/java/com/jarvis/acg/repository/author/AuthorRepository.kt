package com.jarvis.acg.repository.author

import com.jarvis.acg.base.BaseRepository
import com.jarvis.acg.extension.Extension.Companion.join
import com.jarvis.acg.extension.Extension.Companion.toArrayList
import com.jarvis.acg.repository.localDataSource.dao.AuthorDao

class AuthorRepository(
    val remoteDataSource: AuthorRemoteDataSource,
    val authorDao: AuthorDao
) : BaseRepository() {

    suspend fun getAuthorListByIdList(idList: ArrayList<String>) = performGet(
        databaseQuery = { authorDao.getByAuthorListByIdList(idList).toArrayList() },
        networkCall = { remoteDataSource.getAuthorListByIdList(idList.join("_")) },
        saveCallResult = { authorDao.insertAll(it) }
    )

    fun getAuthorListByIdListFromDB(idList: List<String>) = authorDao.getByAuthorListByIdList(idList)
}