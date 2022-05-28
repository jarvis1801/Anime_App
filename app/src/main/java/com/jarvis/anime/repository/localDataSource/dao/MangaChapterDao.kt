package com.jarvis.anime.repository.localDataSource.dao

import androidx.room.*
import com.jarvis.anime.model.mangaChapter.MangaChapter
import com.jarvis.anime.model.mangaChapter.MangaChapterUpdateIsRead
import com.jarvis.anime.model.mangaChapter.MangaChapterUpdateLastPosition
import com.jarvis.anime.model.mangaChapter.MangaChapterUpdateOrigin

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