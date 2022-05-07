package com.jarvis.acg.repository.localDataSource.dao

import androidx.room.*
import com.jarvis.acg.model.mangaChapter.MangaChapter
import com.jarvis.acg.model.mangaChapter.MangaChapterUpdate

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

    @Update(entity = MangaChapter::class)
    fun update(obj: MangaChapterUpdate): Int
}