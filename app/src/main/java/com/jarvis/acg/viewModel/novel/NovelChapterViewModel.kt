package com.jarvis.acg.viewModel.novel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.jarvis.acg.base.BaseViewModel
import com.jarvis.acg.model.Chapter
import com.jarvis.acg.repository.chapter.ChapterRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class NovelChapterViewModel(
    savedStateHandle: SavedStateHandle,
    private val chapterRepository: ChapterRepository
) : BaseViewModel() {

    private val _currentChapter = MutableLiveData<Chapter>()
    val currentChapter = _currentChapter as LiveData<Chapter>

    private val _currentChapterTitle = MutableLiveData<String>()
    val currentChapterTitle = _currentChapterTitle as LiveData<String>

    private val _currentChapterContent = MutableLiveData<String>()
    val currentChapterContent= _currentChapterContent as LiveData<String>

    init {
        changeCurrentChapter(savedStateHandle["chapterId"])
    }


    fun changeCurrentChapter(chapterId: String?) = viewModelScope.launch(IO) {
        chapterId?.let {
            val chapter = chapterRepository.getChapterByIdFromDB(chapterId)
            chapter?.let {
                _currentChapter.postValue(it)
                _currentChapterContent.postValue(it.getChapterContent())
                _currentChapterTitle.postValue(it.getTitle())
            }
        }
    }

//    fun getChapterContent(): String {
//        return _currentChapter.value?.getChapterContent() ?: ""
//    }
//
//    fun getChapterTitle(): String {
//        return _currentChapter.value?.getTitle() ?: ""
//    }
}