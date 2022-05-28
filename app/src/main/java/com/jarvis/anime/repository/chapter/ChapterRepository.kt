package com.jarvis.anime.repository.chapter

import com.jarvis.anime.extension.Extension.Companion.join
import com.jarvis.anime.extension.Extension.Companion.toArrayList
import com.jarvis.anime.model.chapter.Chapter
import com.jarvis.anime.model.chapter.ChapterUpdate
import com.jarvis.anime.repository.localDataSource.dao.ChapterDao

class ChapterRepository(
    val remoteDataSource: ChapterRemoteDataSource,
    val chapterDao: ChapterDao
) : BaseChapterRepository<Chapter>() {

    override suspend fun getChapterListByIdList(idList: ArrayList<String>) = performGet(
        databaseQuery = { chapterDao.getByChapterListByIdList(idList).toArrayList() },
        networkCall = { remoteDataSource.getChapterListByIdList(idList.join("_")) },
        saveCallResult = { list -> list.forEach {
            chapterDao.insertIgnore(it)
            chapterDao.update(ChapterUpdate(it))
        } }
    )

    override fun getChapterListByIdListFromDB(idList: List<String>) = chapterDao.getByChapterListByIdList(idList)

    override fun getChapterByIdFromDB(id: String) = chapterDao.getById(id)

    fun updateChapter(chapter: ChapterUpdate) = chapterDao.update(chapter)

    fun replaceChapter(chapter: Chapter) = chapterDao.insert(chapter)
}