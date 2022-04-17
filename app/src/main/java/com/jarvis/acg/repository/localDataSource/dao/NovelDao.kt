package com.jarvis.acg.repository.localDataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jarvis.acg.model.Novel

@Dao
interface NovelDao {
    @Query("SELECT * FROM novel")
    fun getAll(): List<Novel>?

    @Query("SELECT * FROM novel WHERE id = :id")
    fun getById(id: String?): Novel?

    @Query("SELECT * FROM novel WHERE novel.work_id IN (:idList)")
    fun getByWorkIdList(idList: List<String>): List<Novel>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(obj: List<Novel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: Novel)
}