package com.jarvis.acg.viewModel.manga

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.jarvis.acg.extension.Extension.Companion.toArrayList
import com.jarvis.acg.model.Manga
import com.jarvis.acg.model.mangaChapter.MangaChapter
import com.jarvis.acg.model.mangaChapter.MangaChapterPage
import com.jarvis.acg.model.mangaChapter.MangaChapterPage.Companion.CHAPTER_PAGE_SMOOTH_SCROLL
import com.jarvis.acg.model.mangaChapter.MangaChapterUpdateIsRead
import com.jarvis.acg.model.mangaChapter.MangaChapterUpdateLastPosition
import com.jarvis.acg.model.media.Image
import com.jarvis.acg.repository.author.AuthorRepository
import com.jarvis.acg.repository.chapter.ChapterRepository
import com.jarvis.acg.repository.image.ImageRepository
import com.jarvis.acg.repository.manga.MangaRepository
import com.jarvis.acg.repository.mangaChapter.MangaChapterRepository
import com.jarvis.acg.repository.volume.VolumeRepository
import com.jarvis.acg.repository.work.WorkRepository
import com.jarvis.acg.viewModel.book.BookChapterViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MangaChapterViewModel(
    savedStateHandle: SavedStateHandle,
    mangaRepository: MangaRepository,
    workRepository: WorkRepository,
    authorRepository: AuthorRepository,
    volumeRepository: VolumeRepository,
    chapterRepository: ChapterRepository,
    private val mangaChapterRepository: MangaChapterRepository,
    private val imageRepository: ImageRepository
) : BookChapterViewModel<Manga, MangaChapter>(
    savedStateHandle,
    mangaRepository,
    workRepository,
    authorRepository,
    volumeRepository,
    chapterRepository,
    mangaChapterRepository,
    imageRepository
) {

    override var _currentChapter: MutableLiveData<MangaChapter?> = MutableLiveData()
    val currentChapter = _currentChapter as LiveData<MangaChapter?>

    private val _page = MutableLiveData<MangaChapterPage>()
    val page = _page as LiveData<MangaChapterPage>

    suspend fun getImageList(chapterList: ArrayList<MangaChapter>): HashMap<String, ArrayList<Image>?> {
        val hashMap = hashMapOf<String, ArrayList<Image>?>()
        val request = viewModelScope.async(IO) {

            chapterList.forEach { chapter ->
                val result = async(IO) {
                    chapter.image_id_list?.toArrayList()?.takeIf { it.isNotEmpty() }
                        ?.asSequence()
                        ?.withIndex()?.groupBy { it.index / 10 }
                        ?.map { it.value.map { it.value }.toArrayList() }?.map { idList ->
                            async (IO) { imageRepository.getListByIdList(idList) }
                        }?.map { it.await().data }?.filter { !it.isNullOrEmpty() }
                        ?.flatMap { it!!.asIterable() }
                        ?.toArrayList()
                }
                hashMap[chapter.id] = result.await()
            }
        }
        request.await()
        return hashMap
    }

    fun getCurrentPage(): Int? {
        return _page.value?.page
    }

    fun setCurrentPage(page: Int, scrollType: Int = CHAPTER_PAGE_SMOOTH_SCROLL) {
        _page.postValue(MangaChapterPage(page, scrollType))
    }

    fun getImageCount(): Int {
        return _currentChapter.value?.image_id_list?.size ?: -1
    }

    fun updateChapterLastPosition(currentPage: Int) = viewModelScope.launch(IO) {
        val currentChapter = getCurrentChapter()
        val chapterId = currentChapter?.id

        takeIf { !chapterId.isNullOrEmpty() }?.let {
            currentChapter?.lastPosition = currentPage
            mangaChapterRepository.updateChapterLastPosition(MangaChapterUpdateLastPosition(
                id = chapterId!!,
                lastPosition = currentPage
            ))
        }
    }

    fun enableIsReadWhenLastPage(page: Int, imageCount: Int) = viewModelScope.launch(IO) {
        val currentChapter = getCurrentChapter()
        val chapterId = currentChapter?.id

        takeIf { !chapterId.isNullOrEmpty() && page >= imageCount }?.let {
            currentChapter?.isRead = true
            mangaChapterRepository.updateChapterIsRead(MangaChapterUpdateIsRead(
                id = chapterId!!,
                isRead = true
            ))
        }
    }
}