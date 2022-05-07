package com.jarvis.acg.viewModel.manga

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.jarvis.acg.extension.Extension.Companion.toArrayList
import com.jarvis.acg.model.Manga
import com.jarvis.acg.model.mangaChapter.MangaChapter
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
    mangaChapterRepository: MangaChapterRepository,
    private val imageRepository: ImageRepository
) : BookChapterViewModel<Manga, MangaChapter>(
    savedStateHandle,
    mangaRepository,
    workRepository,
    authorRepository,
    volumeRepository,
    chapterRepository,
    mangaChapterRepository
) {

    override var _currentChapter: MutableLiveData<MangaChapter?> = MutableLiveData()
    val currentChapter = _currentChapter as LiveData<MangaChapter?>

    private var _imageList: MutableLiveData<HashMap<String, ArrayList<Image>?>?> = MutableLiveData()
    val imageList = _imageList as LiveData<HashMap<String, ArrayList<Image>?>?>

    private var _currentChapterImageList: MutableLiveData<ArrayList<Image>?> = MutableLiveData()
    val currentChapterImageList = _currentChapterImageList as LiveData<ArrayList<Image>?>

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
//        _imageList.postValue(hashMap)
    }

    fun getImageListFromDB(chapter: MangaChapter) = viewModelScope.launch(IO) {
        val list = async(IO) {
            chapter.image_id_list?.toArrayList()?.takeIf { it.isNotEmpty() }?.let { idList ->
                imageRepository.getListByIdListFromDB(idList)
            }
        }

        _currentChapterImageList.postValue(list.await())
    }
}