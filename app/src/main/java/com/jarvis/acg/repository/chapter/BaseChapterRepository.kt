package com.jarvis.acg.repository.chapter

import com.jarvis.acg.base.BaseRepository
import com.jarvis.acg.model.BaseChapter
import com.jarvis.acg.model.chapter.ChapterUpdate
import com.jarvis.acg.repository.Resource

abstract class BaseChapterRepository<B: BaseChapter> : BaseRepository() {

    abstract suspend fun getChapterListByIdList(idList: ArrayList<String>): Resource<ArrayList<B>>

    abstract fun getChapterListByIdListFromDB(idList: List<String>): List<B>?

    abstract fun getChapterByIdFromDB(id: String): B?
}