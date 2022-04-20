package com.jarvis.acg.repository.localDataSource.dao

import androidx.room.*
import com.jarvis.acg.model.chapter.Chapter
import com.jarvis.acg.model.chapter.ChapterUpdate

@Dao
interface ChapterDao {
    
    @Query("SELECT * FROM chapter")
    fun getAll(): List<Chapter>?

    @Query("SELECT * FROM chapter WHERE id = :id")
    fun getById(id: String?): Chapter?

    @Query("SELECT * FROM chapter WHERE id IN (:idList)")
    fun getByChapterListByIdList(idList: List<String>): List<Chapter>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(obj: List<Chapter>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: Chapter)

    @Update(entity = Chapter::class)
    fun update(obj: ChapterUpdate): Int
}