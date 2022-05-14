package com.jarvis.acg.viewModel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jarvis.acg.base.BaseViewModel
import com.jarvis.acg.extension.Extension.Companion.join
import com.jarvis.acg.model.BaseChapter
import com.jarvis.acg.model.Book
import com.jarvis.acg.model.Novel
import com.jarvis.acg.model.Work
import com.jarvis.acg.model.media.ImageUpdateImageString
import com.jarvis.acg.repository.Status
import com.jarvis.acg.repository.author.AuthorRepository
import com.jarvis.acg.repository.book.BookRepository
import com.jarvis.acg.repository.chapter.BaseChapterRepository
import com.jarvis.acg.repository.image.ImageRepository
import com.jarvis.acg.repository.library.LibraryRepository
import com.jarvis.acg.repository.painter.PainterRepository
import com.jarvis.acg.repository.publishingHouse.PublishingHouseRepository
import com.jarvis.acg.repository.resource.ResourceRemoteDataSource
import com.jarvis.acg.repository.volume.VolumeRepository
import com.jarvis.acg.repository.work.WorkRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BookSectionViewModel<B: Book, BC: BaseChapter>(
    private val bookRepository: BookRepository<B>,
    private val workRepository: WorkRepository,
    private val painterRepository: PainterRepository,
    private val libraryRepository: LibraryRepository,
    private val publishingHouseRepository: PublishingHouseRepository,
    private val authorRepository: AuthorRepository,
    private val volumeRepository: VolumeRepository,
    private val chapterRepository: BaseChapterRepository<BC>,
    private val imageRepository: ImageRepository
) : BaseViewModel() {

    private var _workList = MutableLiveData<ArrayList<Work>>()
    var workList = _workList as LiveData<ArrayList<Work>>

    fun fetchBook() = viewModelScope.launch(IO) {
        requestApi()
        bookRepository.getBookList().data?.takeIf { !it.isNullOrEmpty() }?.let { bookList ->
            fetchBookDetail(bookList)?.let { workList ->
                _workList.postValue(workList)
            }
        }
    }

    private suspend fun fetchBookDetail(bookList: ArrayList<B>): ArrayList<Work>? = withContext(IO) {
        return@withContext bookList.map { book ->
            val work = async(IO) { getWork(book) }

            withContext(IO) { getPainterList(book) }

            if (book is Novel) {
                withContext(IO) { getLibraryList(book) }
            }

            withContext(IO) { getPublishHouseList(book) }

            withContext(IO) { getAuthorList(book) }

            withContext(IO) { getChapterList(book) }
            work
        }.mapNotNull {
            it.await()
        }.toCollection(ArrayList())
    }

    private suspend fun getWork(book: B): Work? = withContext(IO) {
        book.work_id?.let {
            val work = workRepository.getWorkById(it).data
            work?.apply {
                book_id = book.id
                thumbnail_id_list?.let { imagePrefetchIdList ->
                    val imagePrefetchList = async(IO) { imageRepository.getListByIdList(imagePrefetchIdList) }.await().data

                    val imageStringList = imagePrefetchList?.map { image ->
                        async { image.url?.let { url ->
                            if (image.imageString.isNullOrEmpty()) {
                                val request = ResourceRemoteDataSource().getWorkThumbnail(url)
                                val data = request.takeIf { request.status == Status.SUCCESS }?.data
                                data?.let {
                                    image.imageString = it
                                    imageRepository.updateImageString(ImageUpdateImageString(image))
                                }
                                data
                            } else {
                                image.imageString
                            }
                        } }
                    }?.mapNotNull {
                        it.await()
                    }
                    work.image_byte_list = imageStringList?.toCollection(ArrayList())
                }
            }
            return@withContext work
        }
        return@withContext null
    }

    suspend fun getPainterList(book: Book) {
        book.painter_id_list?.let {
            painter_id_list -> painterRepository.getPainterListByIdList(painter_id_list.join("_"))
        }
    }

    suspend fun getLibraryList(novel: Novel) {
        novel.library_id_list?.let {
            libraryRepository.getLibraryListByIdList(it.join("_"))
        }
    }

    suspend fun getPublishHouseList(book: Book) {
        book.publishing_house_id_list?.let {
            publishingHouseRepository.getPublishingHouseListByIdList(it.join("_"))
        }
    }

    suspend fun getAuthorList(book: Book) {
        book.author_id_list?.let {
            authorRepository.getAuthorListByIdList(it)
        }
    }

    suspend fun getChapterList(book: Book) {
        book.volume_id_list?.let { volumeRepository.getVolumeListByIdList(it) }?.takeIf { it.data != null }?.let { response ->
            response.data?.let { data ->
                if (response.status == Status.SUCCESS) {
                    data.forEach { volume -> volume.chapter_id_list?.let {
                        chapterRepository.getChapterListByIdList(it)
                    } }
                }
            }
        }
    }
}