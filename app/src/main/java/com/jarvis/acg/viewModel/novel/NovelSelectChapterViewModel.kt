package com.jarvis.acg.viewModel.novel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.jarvis.acg.base.BaseViewModel
import com.jarvis.acg.model.Author
import com.jarvis.acg.model.Novel
import com.jarvis.acg.model.Work
import com.jarvis.acg.repository.author.AuthorRepository
import com.jarvis.acg.repository.novel.NovelRepository
import com.jarvis.acg.repository.work.WorkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NovelSelectChapterViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val novelRepository: NovelRepository,
    private val workRepository: WorkRepository,
    private val authorRepository: AuthorRepository
) : BaseViewModel() {

    private val _novel = MutableLiveData<Novel>()
    val novel = _novel as LiveData<Novel>

    private val _work = MutableLiveData<Work>()
    val work = _work as LiveData<Work>

    private val _authorList = MutableLiveData<List<Author>>()
    val authorList = _authorList as LiveData<List<Author>>

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val novel = novelRepository.getNovelByIdFromDB(savedStateHandle["novelId"])
            launch { novel?.let { _novel.postValue(it) } }

            val work = workRepository.getWorkByIdFromDB(novel?.work_id)
            launch { work?.let { _work.postValue(it) } }

            val authorList = authorRepository.getAuthorListByIdListFromDB(novel?.author_id_list ?: arrayListOf())
            launch { authorList?.let { _authorList.postValue(it) } }
        }

    }
}