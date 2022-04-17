package com.jarvis.acg.repository.chapter

import com.jarvis.acg.base.BaseRepository
import com.jarvis.acg.extension.Extension.Companion.join
import com.jarvis.acg.extension.Extension.Companion.toArrayList
import com.jarvis.acg.repository.localDataSource.dao.AuthorDao
import com.jarvis.acg.repository.localDataSource.dao.ChapterDao

class ChapterRepository(
    val remoteDataSource: ChapterRemoteDataSource,
    val chapterDao: ChapterDao
) : BaseRepository() {

    suspend fun getChapterListByIdList(idList: ArrayList<String>) = performGet(
        databaseQuery = { chapterDao.getByChapterListByIdList(idList).toArrayList() },
        networkCall = { remoteDataSource.getChapterListByIdList(idList.join("_")) },
        saveCallResult = { chapterDao.insertAll(it) }
    )

    fun getChapterListByIdListFromDB(idList: List<String>) = chapterDao.getByChapterListByIdList(idList)

    fun getChapterByIdFromDB(id: String) = chapterDao.getById(id)
}