package com.jarvis.acg.repository.manga

import com.jarvis.acg.extension.Extension.Companion.toArrayList
import com.jarvis.acg.model.BookUpdateLastSeen
import com.jarvis.acg.model.BookUpdateOrigin
import com.jarvis.acg.model.Manga
import com.jarvis.acg.repository.book.BookRepository
import com.jarvis.acg.repository.localDataSource.dao.MangaDao

class MangaRepository(
    val remoteDataSource: MangaRemoteDataSource,
    val mangaDao: MangaDao
) : BookRepository<Manga>() {

    override suspend fun getBookList() = performGet(
        databaseQuery = { mangaDao.getAll().toArrayList() },
        networkCall = { remoteDataSource.getMangaList() },
        saveCallResult = { list ->
            list.forEach { manga ->
                mangaDao.insertIgnore(manga)
                mangaDao.update(BookUpdateOrigin((manga)))
            }
        }
    )

    override fun getBookByIdFromDB(id: String?) = mangaDao.getById(id)

    override fun updateBookLastSeen(bookUpdateLastSeen: BookUpdateLastSeen) {
        mangaDao.updateLastSeen(bookUpdateLastSeen)
    }
}