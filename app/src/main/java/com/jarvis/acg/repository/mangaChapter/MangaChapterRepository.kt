package com.jarvis.acg.repository.mangaChapter

import com.jarvis.acg.base.BaseRepository
import com.jarvis.acg.extension.Extension.Companion.join
import com.jarvis.acg.extension.Extension.Companion.toArrayList
import com.jarvis.acg.model.chapter.ChapterUpdate
import com.jarvis.acg.model.mangaChapter.MangaChapterUpdateIsRead
import com.jarvis.acg.model.mangaChapter.MangaChapterUpdateLastPosition
import com.jarvis.acg.model.mangaChapter.MangaChapterUpdateOrigin
import com.jarvis.acg.repository.localDataSource.dao.MangaChapterDao

class MangaChapterRepository(
    val remoteDataSource: MangaChapterRemoteDataSource,
    val mangaChapterDao: MangaChapterDao
) : BaseRepository() {

    suspend fun getChapterListByIdList(idList: ArrayList<String>) = performGet(
        databaseQuery = { mangaChapterDao.getByMangaChapterListByIdList(idList).toArrayList() },
        networkCall = { remoteDataSource.getChapterListByIdList(idList.join("_")) },
        saveCallResult = { list -> list.forEach {
            mangaChapterDao.insertIgnore(it)
            mangaChapterDao.update(MangaChapterUpdateOrigin(it))
        } }
    )

    fun getChapterListByIdListFromDB(idList: List<String>) = mangaChapterDao.getByMangaChapterListByIdList(idList)

    fun getChapterByIdFromDB(id: String) = mangaChapterDao.getById(id)

    fun updateChapterLastPosition(chapter: MangaChapterUpdateLastPosition) = mangaChapterDao.updateLastPosition(chapter)

    fun updateChapterIsRead(chapter: MangaChapterUpdateIsRead) = mangaChapterDao.updateIsRead(chapter)

}