package com.jarvis.acg.repository.book

import com.jarvis.acg.base.BaseRepository
import com.jarvis.acg.model.Book
import com.jarvis.acg.repository.Resource

abstract class BookRepository<B: Book> : BaseRepository() {

    abstract suspend fun getBookList(): Resource<ArrayList<B>>

    abstract fun getBookByIdFromDB(id: String?): B?
}