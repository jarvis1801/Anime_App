package com.jarvis.anime.repository.novel

import com.jarvis.anime.extension.Extension.Companion.toArrayList
import com.jarvis.anime.model.BookUpdateLastSeen
import com.jarvis.anime.model.BookUpdateOrigin
import com.jarvis.anime.model.Novel
import com.jarvis.anime.repository.book.BookRepository
import com.jarvis.anime.repository.localDataSource.dao.NovelDao

class NovelRepository(
    val remoteDataSource: NovelRemoteDataSource,
    val novelDao: NovelDao
) : BookRepository<Novel>() {
    override suspend fun getBookList() = performGet(
        databaseQuery = { novelDao.getAll().toArrayList() },
        networkCall = { remoteDataSource.getNovelList() },
        saveCallResult = { list ->
            list.forEach { novel ->
                novelDao.insertIgnore(novel)
                novelDao.update(BookUpdateOrigin((novel)))
            }
        }
    )

    override fun getBookByIdFromDB(id: String?) = novelDao.getById(id)

    override fun updateBookLastSeen(bookUpdateLastSeen: BookUpdateLastSeen) {
        novelDao.updateLastSeen(bookUpdateLastSeen)
    }
}