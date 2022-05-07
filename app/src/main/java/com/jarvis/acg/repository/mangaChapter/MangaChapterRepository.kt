package com.jarvis.acg.repository.mangaChapter

import com.jarvis.acg.base.BaseRepository
import com.jarvis.acg.extension.Extension.Companion.join
import com.jarvis.acg.extension.Extension.Companion.toArrayList
import com.jarvis.acg.model.mangaChapter.MangaChapterUpdate
import com.jarvis.acg.repository.localDataSource.dao.MangaChapterDao

class MangaChapterRepository(
    val remoteDataSource: MangaChapterRemoteDataSource,
    val mangaChapterDao: MangaChapterDao
) : BaseRepository() {

    suspend fun getChapterListByIdList(idList: ArrayList<String>) = performGet(
        databaseQuery = { mangaChapterDao.getByMangaChapterListByIdList(idList).toArrayList() },
        networkCall = { remoteDataSource.getChapterListByIdList(idList.join("_")) },
        saveCallResult = { mangaChapterDao.insertAll(it) }
    )

    fun getChapterListByIdListFromDB(idList: List<String>) = mangaChapterDao.getByMangaChapterListByIdList(idList)

    fun getChapterByIdFromDB(id: String) = mangaChapterDao.getById(id)

    fun updateChapter(chapter: MangaChapterUpdate) = mangaChapterDao.update(chapter)
}