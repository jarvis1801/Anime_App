package com.jarvis.anime.repository.localDataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jarvis.anime.model.Work

@Dao
interface WorkDao {
    @Query("SELECT * FROM work")
    fun getAll(): List<Work>?

    @Query("SELECT * FROM work WHERE id = :id")
    fun getById(id: String?): Work?

    @Query("SELECT * FROM work WHERE id IN (:idList)")
    fun getByWorkListByIdList(idList: List<String>): List<Work>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(obj: List<Work>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: Work)
}