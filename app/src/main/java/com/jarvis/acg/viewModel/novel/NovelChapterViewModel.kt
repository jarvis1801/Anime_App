package com.jarvis.acg.viewModel.novel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.jarvis.acg.base.BaseViewModel
import com.jarvis.acg.extension.Extension.Companion.toArrayList
import com.jarvis.acg.model.Volume
import com.jarvis.acg.model.chapter.Chapter
import com.jarvis.acg.model.chapter.ChapterUpdate
import com.jarvis.acg.repository.chapter.ChapterRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class NovelChapterViewModel(
    val savedStateHandle: SavedStateHandle,
    private val chapterRepository: ChapterRepository
) : BaseViewModel() {

    private val _currentChapter = MutableLiveData<Chapter>()
    val currentChapter = _currentChapter as LiveData<Chapter>

    val contentLineTopList = arrayListOf<Int>()
    var currentLine: Int = 0

    fun initCurrentChapter(volumeChapterList: ArrayList<Any>?) {
        changeCurrentChapter(savedStateHandle["chapterId"], volumeChapterList)
    }

    fun changeCurrentChapter(chapterId: String?, volumeChapterList: ArrayList<Any>?) = viewModelScope.launch(IO) {
        chapterId?.let {
            chapterRepository.getChapterByIdFromDB(chapterId)?.let { chapter ->
                if (volumeChapterList != null) {
                    val volumeList = volumeChapterList.filterIsInstance<Volume>().toArrayList()

                    val currentVolume = volumeChapterList.find {
                        it is Volume && it.id == chapter.volume_id
                    } as Volume

                    val currentChapterList = volumeChapterList.filter { it is Chapter && currentVolume.id == it.volume_id }.map {
                        it as Chapter
                    }.toArrayList()

                    currentChapterList.indexOfFirst { it.id == chapter.id }.takeIf { it != -1 }?.let {
                        if (currentChapterList.size > it + 1) {
                            val nextChapterId = currentChapterList[it + 1].id
                            chapter.nextChapterId = nextChapterId
                        } else {
                            volumeList.indexOf(currentVolume).takeIf { it + 1 < volumeList.size }?.let {
                                val nextVolume = volumeList[it + 1]

                                val nextChapterList = volumeChapterList.filter {
                                    it is Chapter && nextVolume.id == it.volume_id
                                }.map { it as Chapter }.toArrayList()

                                chapter.nextChapterId = nextChapterList.firstOrNull()?.id ?: ""
                            }
                        }

                        if (0 <= (it - 1)) {
                            val prevChapterId = currentChapterList[it - 1].id
                            chapter.prevChapterId = prevChapterId
                        } else {
                            volumeList.indexOf(currentVolume).takeIf { it > 0 }?.let {
                                val prevVolume = volumeList[it - 1]

                                val prevChapterList = volumeChapterList.filter {
                                    it is Chapter && prevVolume.id == it.volume_id
                                }.map { it as Chapter }.toArrayList()

                                chapter.prevChapterId = prevChapterList.lastOrNull()?.id ?: ""
                            }
                        }
                    }
                }
                _currentChapter.postValue(chapter)
            }
        }
    }

    fun onNextChapterClick(volumeChapterList: ArrayList<Any>?) {
        getCurrentChapterValue()?.let { chapter ->
            changeCurrentChapter(chapter.nextChapterId, volumeChapterList)
        }
    }

    fun onPrevChapterClick(volumeChapterList: ArrayList<Any>?) {
        getCurrentChapterValue()?.let { chapter ->
            changeCurrentChapter(chapter.prevChapterId, volumeChapterList)
        }
    }

    fun getCurrentChapterValue(): Chapter? {
        return _currentChapter.value
    }

    fun updateCurrentChapterCurrentLine(currentLine: Int) {
        updateCurrentChapter(currentLine)
    }

    fun updateCurrentChapterIsRead(isRead: Boolean) {
        updateCurrentChapter(isRead = isRead)
    }

    private fun updateCurrentChapter(currentLine: Int? = null, isRead: Boolean? = null) = viewModelScope.launch(IO) {
        currentChapter.value?.let { chapter ->
            if (isRead == true && chapter.isRead == true) { return@launch }
            chapter.apply {
                currentLine?.let { this.currentLine = currentLine }
                isRead?.let { this.isRead = isRead }
                chapterRepository.updateChapter(ChapterUpdate(chapter))
            }
        }
    }
}