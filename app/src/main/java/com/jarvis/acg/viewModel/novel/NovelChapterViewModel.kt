package com.jarvis.acg.viewModel.novel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.jarvis.acg.App
import com.jarvis.acg.model.Novel
import com.jarvis.acg.model.chapter.Chapter
import com.jarvis.acg.model.chapter.ChapterUpdate
import com.jarvis.acg.repository.author.AuthorRepository
import com.jarvis.acg.repository.chapter.ChapterRepository
import com.jarvis.acg.repository.image.ImageRemoteDataSource
import com.jarvis.acg.repository.image.ImageRepository
import com.jarvis.acg.repository.mangaChapter.MangaChapterRepository
import com.jarvis.acg.repository.novel.NovelRepository
import com.jarvis.acg.repository.volume.VolumeRepository
import com.jarvis.acg.repository.work.WorkRepository
import com.jarvis.acg.viewModel.book.BookChapterViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NovelChapterViewModel(
    savedStateHandle: SavedStateHandle,
    novelRepository: NovelRepository,
    workRepository: WorkRepository,
    authorRepository: AuthorRepository,
    volumeRepository: VolumeRepository,
    val chapterRepository: ChapterRepository,
    mangaChapterRepository: MangaChapterRepository,
    imageRepository: ImageRepository
) : BookChapterViewModel<Novel, Chapter>(
    savedStateHandle,
    novelRepository,
    workRepository,
    authorRepository,
    volumeRepository,
    chapterRepository,
    mangaChapterRepository,
    imageRepository
) {
    override var _currentChapter: MutableLiveData<Chapter?> = MutableLiveData()
    val currentChapter = _currentChapter as LiveData<Chapter?>

    val contentLineTopList = arrayListOf<Int>()
    var currentLine: Int = 0

    fun getCurrentChapterValue(): Chapter? {
        return _currentChapter.value
    }

    fun onNextChapterClick(successCallback: () -> Unit) {
        getCurrentChapterValue()?.let { chapter ->
            val chapterList = getVolumeChapterList()?.filterIsInstance<Chapter>()
            chapterList?.indexOfFirst {
                it.id == chapter.id
            }?.takeIf { it + 1 < chapterList.size }?.let {
                successCallback()
                setCurrentChapter<Chapter>(chapterList[it + 1])
                return
            }
        }
    }

    fun onPrevChapterClick(successCallback: () -> Unit) {
        getCurrentChapterValue()?.let { chapter ->
            val chapterList = getVolumeChapterList()?.filterIsInstance<Chapter>()
            chapterList?.indexOfFirst {
                it.id == chapter.id
            }?.takeIf { it - 1 >= 0 }?.let {
                successCallback()
                setCurrentChapter<Chapter>(chapterList[it - 1])
                return
            }
        }
    }

    fun updateCurrentChapterIsRead(isRead: Boolean) {
        updateCurrentChapter(isRead = isRead)
    }

    private fun updateCurrentChapter(currentLine: Int? = null, isRead: Boolean? = null) = viewModelScope.launch(Dispatchers.IO) {
        currentChapter.value?.let { chapter ->
            if (isRead == true && chapter.isRead == true) { return@launch }
            chapter.apply {
                currentLine?.let { this.currentLine = currentLine }
                isRead?.let { this.isRead = isRead }
                chapterRepository.replaceChapter(chapter)
            }
        }
    }

    fun updateCurrentChapterCurrentLine(currentLine: Int) {
        updateCurrentChapter(currentLine)
    }
}