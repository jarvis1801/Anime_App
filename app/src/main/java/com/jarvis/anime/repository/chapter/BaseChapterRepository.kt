package com.jarvis.anime.repository.chapter

import com.jarvis.anime.base.BaseRepository
import com.jarvis.anime.model.BaseChapter
import com.jarvis.anime.repository.Resource

abstract class BaseChapterRepository<B: BaseChapter> : BaseRepository() {

    abstract suspend fun getChapterListByIdList(idList: ArrayList<String>): Resource<ArrayList<B>>

    abstract fun getChapterListByIdListFromDB(idList: List<String>): List<B>?

    abstract fun getChapterByIdFromDB(id: String): B?
}