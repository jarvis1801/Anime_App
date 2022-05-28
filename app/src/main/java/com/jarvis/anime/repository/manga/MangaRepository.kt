package com.jarvis.anime.repository.manga

import com.jarvis.anime.extension.Extension.Companion.toArrayList
import com.jarvis.anime.model.BookUpdateLastSeen
import com.jarvis.anime.model.BookUpdateOrigin
import com.jarvis.anime.model.Manga
import com.jarvis.anime.repository.book.BookRepository
import com.jarvis.anime.repository.localDataSource.dao.MangaDao

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