package com.jarvis.anime.repository.author

import com.jarvis.anime.base.BaseRepository
import com.jarvis.anime.extension.Extension.Companion.join
import com.jarvis.anime.extension.Extension.Companion.toArrayList
import com.jarvis.anime.repository.localDataSource.dao.AuthorDao

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