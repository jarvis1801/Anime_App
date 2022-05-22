package com.jarvis.acg.repository.novel

import com.jarvis.acg.extension.Extension.Companion.toArrayList
import com.jarvis.acg.model.BookUpdateLastSeen
import com.jarvis.acg.model.BookUpdateOrigin
import com.jarvis.acg.model.Novel
import com.jarvis.acg.repository.book.BookRepository
import com.jarvis.acg.repository.localDataSource.dao.NovelDao

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