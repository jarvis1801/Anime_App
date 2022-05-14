package com.jarvis.acg.viewModel.book

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.jarvis.acg.base.BaseViewModel
import com.jarvis.acg.extension.Extension.Companion.toArrayList
import com.jarvis.acg.model.*
import com.jarvis.acg.model.chapter.Chapter
import com.jarvis.acg.model.mangaChapter.MangaChapter
import com.jarvis.acg.repository.author.AuthorRepository
import com.jarvis.acg.repository.book.BookRepository
import com.jarvis.acg.repository.chapter.ChapterRepository
import com.jarvis.acg.repository.mangaChapter.MangaChapterRepository
import com.jarvis.acg.repository.resource.ResourceRemoteDataSource
import com.jarvis.acg.repository.volume.VolumeRepository
import com.jarvis.acg.repository.work.WorkRepository
import com.jarvis.acg.util.DateTimeUtil
import com.jarvis.acg.util.DateTimeUtil.convertDateToTimestamp
import com.jarvis.acg.viewModel.manga.MangaChapterViewModel
import com.jarvis.acg.viewModel.novel.NovelChapterViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BookChapterViewModel<B : Book, C: BaseChapter>(
    private val savedStateHandle: SavedStateHandle,
    private val bookRepository: BookRepository<B>,
    private val workRepository: WorkRepository,
    private val authorRepository: AuthorRepository,
    private val volumeRepository: VolumeRepository,
    private val chapterRepository: ChapterRepository,
    private val mangaChapterRepository: MangaChapterRepository,
) : BaseViewModel() {

    private val _book = MutableLiveData<B?>()
    val book = _book as LiveData<B?>

    private val _work = MutableLiveData<Work?>()
    val work = _work as LiveData<Work?>

    private val _authorList = MutableLiveData<List<Author>?>()
    val authorList = _authorList as LiveData<List<Author>?>

    private var _volumeChapterList: MutableLiveData<ArrayList<Any>?> = MutableLiveData()
    var volumeChapterList = _volumeChapterList as LiveData<ArrayList<Any>?>

    abstract var _currentChapter: MutableLiveData<C?>

    private var _notifyIndexChanged = MutableLiveData<Int?>(null)
    var notifyIndexChanged = _notifyIndexChanged as LiveData<Int?>

    private var _lastSeenTitle = MutableLiveData<String?>()
    var lastSeenChapterTitle = _lastSeenTitle as LiveData<String?>

    fun fetchAllInfo(bookId: String) = viewModelScope.launch(IO) {
        requestApi()
        val book = bookRepository.getBookByIdFromDB(bookId)
        launch { book?.let {
            val volumeChapterList = fetchVolumeChapterList(it)
            if (this@BookChapterViewModel is MangaChapterViewModel) {
                val imageMapping = getImageList(volumeChapterList.filterIsInstance<MangaChapter>().toArrayList())
                volumeChapterList.filterIsInstance<MangaChapter>().forEach { chapter ->
                    chapter.imageList = imageMapping[chapter.id]
                }
            }
            _volumeChapterList.postValue(volumeChapterList)
            setBook(it)
            requestApiFinished()
        } }

        val work = workRepository.getWorkByIdFromDB(book?.work_id)
        launch { work?.let { _work.postValue(it) } }

        val authorList = authorRepository.getAuthorListByIdListFromDB(book?.author_id_list ?: arrayListOf())
        launch { authorList?.let { _authorList.postValue(it) } }
    }

    private suspend fun fetchVolumeChapterList(novel: Book): ArrayList<Any> {
        val request = viewModelScope.async(IO) {
            novel.volume_id_list?.let { volumeRepository.getVolumeListByIdListFromDB(it) }
                ?.let { data ->
                    convertVolumeChapterList(data)
                } ?: arrayListOf()
        }
        val list = request.await()
        return list
    }

    private fun convertVolumeChapterList(data: List<Volume>): ArrayList<Any> {
        val resultList = arrayListOf<Any>()
        val sortedVolumeList = data.sortedWith(compareBy(
            { it.order },
            { it.created_at?.convertDateToTimestamp(DateTimeUtil.FORMAT_SERVER_TIME)}
        ))

        sortedVolumeList.forEach { volume ->
            resultList.add(volume)
            volume.chapter_id_list?.let { chapterIdList ->

                val chapterList = when (this) {
                    is MangaChapterViewModel -> mangaChapterRepository.getChapterListByIdListFromDB(chapterIdList)
                    is NovelChapterViewModel -> chapterRepository.getChapterListByIdListFromDB(chapterIdList)
                    else -> arrayListOf()
                }

                chapterList.let {
                    val sortedChapterList = chapterList?.sortedWith(compareBy(
                        { it.order },
                        { it.created_at?.convertDateToTimestamp(DateTimeUtil.FORMAT_SERVER_TIME)}
                    ))

                    sortedChapterList?.let {
                        resultList.addAll(sortedChapterList)
                    }
                }
            }
        }
        return resultList
    }

    fun <T: BaseChapter> setCurrentChapter(chapter: C) {
        _currentChapter.postValue(chapter)
    }

    fun setCurrentMangaChapterById(id: String) = viewModelScope.launch(IO) {
        _volumeChapterList.value?.filterIsInstance<MangaChapter>()?.find { it.id == id }?.let { chapter ->
            getImageResource(chapter)
            setCurrentChapter<MangaChapter>(chapter as C)
        }
    }

    private suspend fun getImageResource(chapter: MangaChapter) = viewModelScope.launch(IO) {

        val lastPosition = chapter.lastPosition ?: 0
        val nextPosition = lastPosition + 1
        val prevPosition = lastPosition - 1
        val notifyList = arrayListOf(lastPosition)
        if (prevPosition >= 0) notifyList.add(prevPosition)
        if (nextPosition < chapter.imageList?.size ?: 0) notifyList.add(nextPosition)

        chapter.imageList?.mapIndexed { index, item -> item.url }?.mapIndexed { index, url ->
            async(IO) {
                if (url != null) {
                    val imageRequest = ResourceRemoteDataSource().getImageResource(url)
                    chapter.imageList!![index].imageString = imageRequest.data

                    if (notifyList.contains(index))
                        _notifyIndexChanged.postValue(index)
                }
            }
        }?.map { it.await() }
    }

    fun setCurrentChapterById(id: String) = viewModelScope.launch(IO) {
        _volumeChapterList.value?.filterIsInstance<Chapter>()?.find { it.id == id }?.let { chapter ->
            setCurrentChapter<Chapter>(chapter as C)
        }
    }

    fun getVolumeChapterList(): ArrayList<Any>? {
        return _volumeChapterList.value
    }

    fun getCurrentChapter(): C? {
        return _currentChapter.value
    }

    fun getBook(): B? {
        return _book.value
    }

    private fun setBook(book: B) {
        _book.postValue(book)
    }

    private fun setLastSeenTitle(title: String?) {
        _lastSeenTitle.postValue(title)
    }

    open fun resetViewModel() {
        _book.postValue(null)
        _work.postValue(null)
        _authorList.postValue(null)
        _volumeChapterList.postValue(null)
        _currentChapter.postValue(null)
    }

    fun resetViewModelForChapterPage() {
        _currentChapter.postValue(null)
    }

    fun updateBookLastSeen(baseChapter: BaseChapter) = viewModelScope.launch(IO) {
        getBook()?.let { book ->
            val volumeId = baseChapter.volume_id
            val chapterId = baseChapter.id

            book.last_volume_id = volumeId
            book.last_chapter_id = chapterId
            setBook(book)

            // update book to DB
            bookRepository.updateBookLastSeen(BookUpdateLastSeen(book))
        }
    }

    fun getLastSeenTitle(book: B) {
        _volumeChapterList.value?.let { list ->
            val chapterTitle = list.find { it is BaseChapter && it.id == book.id }?.run {
                (this as BaseChapter).name?.getValue()
            }

            val volumeTitle = list.find { it is Volume && it.id == book.id }?.run {
                (this as Volume).name?.getValue()
            }

            if (chapterTitle == null) {
                setLastSeenTitle(null)
            } else {
                setLastSeenTitle("$volumeTitle - $chapterTitle")
            }
        }
    }
}