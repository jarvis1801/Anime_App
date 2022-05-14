package com.jarvis.acg.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jarvis.acg.base.BaseViewModel
import com.jarvis.acg.extension.Extension.Companion.join
import com.jarvis.acg.model.Book
import com.jarvis.acg.model.Novel
import com.jarvis.acg.model.Work
import com.jarvis.acg.repository.Status
import com.jarvis.acg.repository.author.AuthorRepository
import com.jarvis.acg.repository.chapter.ChapterRepository
import com.jarvis.acg.repository.image.ImageRepository
import com.jarvis.acg.repository.library.LibraryRepository
import com.jarvis.acg.repository.manga.MangaRepository
import com.jarvis.acg.repository.mangaChapter.MangaChapterRepository
import com.jarvis.acg.repository.novel.NovelRepository
import com.jarvis.acg.repository.painter.PainterRepository
import com.jarvis.acg.repository.publishingHouse.PublishingHouseRepository
import com.jarvis.acg.repository.volume.VolumeRepository
import com.jarvis.acg.repository.work.WorkRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val novelRepository: NovelRepository,
    private val mangaRepository: MangaRepository,
    private val workRepository: WorkRepository,
    private val painterRepository: PainterRepository,
    private val libraryRepository: LibraryRepository,
    private val publishingHouseRepository: PublishingHouseRepository,
    private val authorRepository: AuthorRepository,
    private val volumeRepository: VolumeRepository,
    private val chapterRepository: ChapterRepository,
    private val mangaChapterRepository: MangaChapterRepository,
    private val imageRepository: ImageRepository
) : BaseViewModel() {
    private var _novelWorkList = MutableLiveData<ArrayList<Work>>()
    var novelWorkList = _novelWorkList as LiveData<ArrayList<Work>>

    private var _notifyNovelWorkIndexChanged = MutableLiveData<Int?>(null)
    var notifyNovelWorkIndexChanged = _notifyNovelWorkIndexChanged as LiveData<Int?>

    private var _mangaWorkList = MutableLiveData<ArrayList<Work>>()
    var mangaWorkList = _mangaWorkList as LiveData<ArrayList<Work>>

    fun fetchList() {
        fetchNovelList()
        fetchMangaList()
        fetchAnimeList()
    }

    private fun fetchNovelList() {
        viewModelScope.launch(IO) {
            requestApi()
            val resource = novelRepository.getBookList()
            val novelWorkList = resource.data.takeIf { it != null && it.size > 0 }?.let { fetchBookDetail(it, "novel") }
            novelWorkList?.let { _novelWorkList.postValue(it) }
        }
    }

    private suspend fun <T : Book> fetchBookDetail(novelList: ArrayList<T>, acgType: String): ArrayList<Work> = withContext(IO) {
        val workList = arrayListOf<Work>()
        novelList.forEach { book ->
            withContext(IO) {
                book.work_id?.let {
                    val work = workRepository.getWorkById(it).data
                    work?.apply {
                        book_id = book.id
                        workList.add(work)
                    }
                }
            }

            withContext(IO) { book.painter_id_list?.let {
                painterRepository.getPainterListByIdList(it.join("_"))
            } }

            if (book is Novel) {
                withContext(IO) { book.library_id_list?.let {
                    libraryRepository.getLibraryListByIdList(it.join("_"))
                } }
            }

            withContext(IO) { book.publishing_house_id_list?.let {
                publishingHouseRepository.getPublishingHouseListByIdList(it.join("_"))
            } }

            withContext(IO) { book.author_id_list?.let {
                authorRepository.getAuthorListByIdList(it)
            } }

            withContext(IO) {
                book.volume_id_list?.let { volumeRepository.getVolumeListByIdList(it) }?.takeIf { it.data != null }?.let { response ->
                    response.data?.let { data ->
                        if (response.status == Status.SUCCESS) {
                            data.forEach { volume -> volume.chapter_id_list?.let {
                                if (acgType == "novel") {
                                    chapterRepository.getChapterListByIdList(it)
                                } else if (acgType == "manga") {
                                    mangaChapterRepository.getChapterListByIdList(it)
                                }
                            } }
                        }
                    }
                }
            }
        }
        workList
    }

    private fun fetchMangaList() {
        viewModelScope.launch(IO) {
            requestApi()
            val resource = mangaRepository.getBookList()
            val mangaWorkList = resource.data.takeIf { it != null && it.size > 0 }?.let { fetchBookDetail(it, "manga") }
            mangaWorkList.takeIf { it != null }?.let { _mangaWorkList.postValue(mangaWorkList!!) }
        }
    }

    private fun fetchAnimeList() {
    }
}