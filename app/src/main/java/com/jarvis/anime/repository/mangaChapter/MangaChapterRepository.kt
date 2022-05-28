package com.jarvis.anime.repository.mangaChapter

import com.jarvis.anime.extension.Extension.Companion.join
import com.jarvis.anime.extension.Extension.Companion.toArrayList
import com.jarvis.anime.model.mangaChapter.MangaChapter
import com.jarvis.anime.model.mangaChapter.MangaChapterUpdateIsRead
import com.jarvis.anime.model.mangaChapter.MangaChapterUpdateLastPosition
import com.jarvis.anime.model.mangaChapter.MangaChapterUpdateOrigin
import com.jarvis.anime.repository.chapter.BaseChapterRepository
import com.jarvis.anime.repository.localDataSource.dao.MangaChapterDao

class MangaChapterRepository(
    val remoteDataSource: MangaChapterRemoteDataSource,
    val mangaChapterDao: MangaChapterDao
) : BaseChapterRepository<MangaChapter>() {

    override suspend fun getChapterListByIdList(idList: ArrayList<String>) = performGet(
        databaseQuery = { mangaChapterDao.getByMangaChapterListByIdList(idList).toArrayList() },
        networkCall = { remoteDataSource.getChapterListByIdList(idList.join("_")) },
        saveCallResult = { list -> list.forEach {
            mangaChapterDao.insertIgnore(it)
            mangaChapterDao.update(MangaChapterUpdateOrigin(it))
        } }
    )

    override fun getChapterListByIdListFromDB(idList: List<String>) = mangaChapterDao.getByMangaChapterListByIdList(idList)

    override fun getChapterByIdFromDB(id: String) = mangaChapterDao.getById(id)

    fun updateChapterLastPosition(chapter: MangaChapterUpdateLastPosition) = mangaChapterDao.updateLastPosition(chapter)

    fun updateChapterIsRead(chapter: MangaChapterUpdateIsRead) = mangaChapterDao.updateIsRead(chapter)

}