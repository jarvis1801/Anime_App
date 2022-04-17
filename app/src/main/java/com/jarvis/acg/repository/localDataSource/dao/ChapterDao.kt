package com.jarvis.acg.repository.localDataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jarvis.acg.model.Chapter

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
}