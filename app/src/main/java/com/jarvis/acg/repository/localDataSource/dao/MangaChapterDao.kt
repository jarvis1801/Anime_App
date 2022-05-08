package com.jarvis.acg.repository.localDataSource.dao

import androidx.room.*
import com.jarvis.acg.model.mangaChapter.MangaChapter
import com.jarvis.acg.model.mangaChapter.MangaChapterUpdateIsRead
import com.jarvis.acg.model.mangaChapter.MangaChapterUpdateLastPosition
import com.jarvis.acg.model.mangaChapter.MangaChapterUpdateOrigin

@Dao
interface MangaChapterDao {

    @Query("SELECT * FROM mangaChapter")
    fun getAll(): List<MangaChapter>?

    @Query("SELECT * FROM mangaChapter WHERE id = :id")
    fun getById(id: String?): MangaChapter?

    @Query("SELECT * FROM mangaChapter WHERE id IN (:idList)")
    fun getByMangaChapterListByIdList(idList: List<String>): List<MangaChapter>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(obj: List<MangaChapter>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: MangaChapter)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(obj: MangaChapter)

    @Update(entity = MangaChapter::class)
    fun updateLastPosition(obj: MangaChapterUpdateLastPosition): Int

    @Update(entity = MangaChapter::class)
    fun updateIsRead(obj: MangaChapterUpdateIsRead): Int


    @Update(entity = MangaChapter::class)
    fun update(obj: MangaChapterUpdateOrigin): Int
}