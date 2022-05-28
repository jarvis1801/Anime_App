package com.jarvis.anime.repository.book

import com.jarvis.anime.base.BaseRepository
import com.jarvis.anime.model.Book
import com.jarvis.anime.model.BookUpdateLastSeen
import com.jarvis.anime.repository.Resource

abstract class BookRepository<B: Book> : BaseRepository() {

    abstract suspend fun getBookList(): Resource<ArrayList<B>>

    abstract fun getBookByIdFromDB(id: String?): B?

    abstract fun updateBookLastSeen(bookUpdateLastSeen: BookUpdateLastSeen)
}