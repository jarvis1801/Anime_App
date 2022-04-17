package com.jarvis.acg.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jarvis.acg.base.BaseViewModel
import com.jarvis.acg.extension.Extension.Companion.join
import com.jarvis.acg.extension.Extension.Companion.toArrayList
import com.jarvis.acg.model.Chapter
import com.jarvis.acg.model.Novel
import com.jarvis.acg.model.Volume
import com.jarvis.acg.model.Work
import com.jarvis.acg.repository.author.AuthorRepository
import com.jarvis.acg.repository.chapter.ChapterRepository
import com.jarvis.acg.repository.library.LibraryRepository
import com.jarvis.acg.repository.novel.NovelRepository
import com.jarvis.acg.repository.painter.PainterRepository
import com.jarvis.acg.repository.publishingHouse.PublishingHouseRepository
import com.jarvis.acg.repository.volume.VolumeRepository
import com.jarvis.acg.repository.work.WorkRepository
import com.jarvis.acg.util.DateTimeUtil
import com.jarvis.acg.util.DateTimeUtil.FORMAT_SERVER_TIME
import com.jarvis.acg.util.DateTimeUtil.convertDateToTimestamp
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    val novelRepository: NovelRepository,
    val workRepository: WorkRepository,
    val painterRepository: PainterRepository,
    val libraryRepository: LibraryRepository,
    val publishingHouseRepository: PublishingHouseRepository,
    val authorRepository: AuthorRepository,
    val volumeRepository: VolumeRepository,
    val chapterRepository: ChapterRepository
) : BaseViewModel() {
    private var _novelWorkList = MutableLiveData<ArrayList<Work>>()
    var novelWorkList = _novelWorkList as LiveData<ArrayList<Work>>

    private var _novelVolumeChapterList: MutableLiveData<ArrayList<Any>> = MutableLiveData()
    var novelVolumeChapterList = _novelVolumeChapterList as LiveData<ArrayList<Any>>

    init {
        fetchNovelList()
        fetchMangaList()
        fetchAnimeList()
    }

    private fun fetchNovelList() {
        viewModelScope.launch(IO) {
            requestApi()
            val resource = novelRepository.getNovelList()
            val novelWorkList = resource.data.takeIf { it != null && it.size > 0 }?.let { fetchNovelDetail(it) }
            novelWorkList.takeIf { it != null }?.let { _novelWorkList.postValue(novelWorkList!!) }

            requestApiFinished()
        }
    }

    private suspend fun fetchNovelDetail(novelList: ArrayList<Novel>): ArrayList<Work> = withContext(IO) {
        val novelWorkList = arrayListOf<Work>()
        novelList.forEach { novel ->
            launch { novel.work_id?.let {
                val work = workRepository.getWorkById(it).data
                work?.apply {
                    novel_id = novel.id
                    novelWorkList.add(work)
                }
            } }

            launch { novel.painter_id_list?.let {
                painterRepository.getPainterListByIdList(it.join("_"))
            } }

            launch { novel.library_id_list?.let {
                libraryRepository.getLibraryListByIdList(it.join("_"))
            } }

            launch { novel.publishing_house_id_list?.let {
                publishingHouseRepository.getPublishingHouseListByIdList(it.join("_"))
            } }

            launch { novel.author_id_list?.let {
                authorRepository.getAuthorListByIdList(it)
            } }

            launch {
                novel.volume_id_list?.let { volumeRepository.getVolumeListByIdList(it) }?.takeIf { it.data != null }?.let { response ->
                    response.data?.let { data ->
                        data.forEach { volume -> volume.chapter_id_list?.let { chapterRepository.getChapterListByIdList(it) } }
                    }
                }
            }
        }
        novelWorkList
    }

    private fun fetchMangaList() {
    }

    private fun fetchAnimeList() {
    }

    /**
     * Volume and Chapter can be using in some diff location, so save in activity viewModel
     * Fetch Volume -> Fetch Chapter -> select page observe value -> get value to display logic
     */
    fun fetchNovelVolumeList(novel: Novel) {
        viewModelScope.launch(IO) {
            novel.volume_id_list?.let { volumeRepository.getVolumeListByIdListFromDB(it) }?.let { data ->

                val novelVolumeChapterList = convertNovelVolumeChapterList(data)

                novelVolumeChapterList.takeIf { it.isNotEmpty() }?.let {
                    _novelVolumeChapterList.postValue(novelVolumeChapterList)
                }
            }
        }
    }

    private fun convertNovelVolumeChapterList(data: List<Volume>): ArrayList<Any> {
        // TODO SORTING
        val resultList = arrayListOf<Any>()
        val sortedVolumeList = data.sortedWith(compareBy(
            { it.order },
            { it.created_at?.convertDateToTimestamp(FORMAT_SERVER_TIME)}
        ))

        sortedVolumeList.forEach { volume ->
            volume.chapter_id_list?.let { chapterIdList ->

                chapterRepository.getChapterListByIdListFromDB(chapterIdList).let { chapterList ->
                    val sortedChapterList = chapterList?.sortedWith(compareBy(
                        { it.order },
                        { it.created_at?.convertDateToTimestamp(FORMAT_SERVER_TIME)}
                    ))

                    sortedChapterList?.takeIf { sortedChapterList.isNotEmpty() }?.let {
                        resultList.add(volume)
                        resultList.addAll(sortedChapterList)
                    }
                }
            }
        }
        return resultList
    }

    fun getNovelVolumeChapterList(): MutableLiveData<ArrayList<Any>> {
        return _novelVolumeChapterList
    }
}