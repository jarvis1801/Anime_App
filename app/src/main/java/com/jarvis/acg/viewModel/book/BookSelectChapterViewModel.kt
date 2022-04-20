package com.jarvis.acg.viewModel.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.jarvis.acg.base.BaseViewModel
import com.jarvis.acg.model.Author
import com.jarvis.acg.model.Book
import com.jarvis.acg.model.Work
import com.jarvis.acg.repository.author.AuthorRepository
import com.jarvis.acg.repository.book.BookRepository
import com.jarvis.acg.repository.work.WorkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BookSelectChapterViewModel<B : Book>(
    private val savedStateHandle: SavedStateHandle,
    private val bookRepository: BookRepository<B>,
    private val workRepository: WorkRepository,
    private val authorRepository: AuthorRepository
) : BaseViewModel() {

    private val _book = MutableLiveData<B>()
    val book = _book as LiveData<B>

    private val _work = MutableLiveData<Work>()
    val work = _work as LiveData<Work>

    private val _authorList = MutableLiveData<List<Author>>()
    val authorList = _authorList as LiveData<List<Author>>

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val book = bookRepository.getBookByIdFromDB(savedStateHandle["novelId"])
            launch { book?.let { _book.postValue(it) } }

            val work = workRepository.getWorkByIdFromDB(book?.work_id)
            launch { work?.let { _work.postValue(it) } }

            val authorList = authorRepository.getAuthorListByIdListFromDB(book?.author_id_list ?: arrayListOf())
            launch { authorList?.let { _authorList.postValue(it) } }
        }

    }
}