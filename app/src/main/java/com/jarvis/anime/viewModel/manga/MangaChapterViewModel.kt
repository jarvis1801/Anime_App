package com.jarvis.anime.viewModel.manga

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.jarvis.anime.extension.Extension.Companion.toArrayList
import com.jarvis.anime.model.Manga
import com.jarvis.anime.model.mangaChapter.MangaChapter
import com.jarvis.anime.model.mangaChapter.MangaChapterPage
import com.jarvis.anime.model.mangaChapter.MangaChapterPage.Companion.CHAPTER_PAGE_SMOOTH_SCROLL
import com.jarvis.anime.model.mangaChapter.MangaChapterUpdateIsRead
import com.jarvis.anime.model.mangaChapter.MangaChapterUpdateLastPosition
import com.jarvis.anime.model.media.Image
import com.jarvis.anime.repository.author.AuthorRepository
import com.jarvis.anime.repository.chapter.ChapterRepository
import com.jarvis.anime.repository.image.ImageRepository
import com.jarvis.anime.repository.manga.MangaRepository
import com.jarvis.anime.repository.mangaChapter.MangaChapterRepository
import com.jarvis.anime.repository.volume.VolumeRepository
import com.jarvis.anime.repository.work.WorkRepository
import com.jarvis.anime.viewModel.book.BookChapterViewModel
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