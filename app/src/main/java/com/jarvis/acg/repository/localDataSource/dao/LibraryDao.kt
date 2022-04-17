package com.jarvis.acg.repository.localDataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jarvis.acg.model.Library

@Dao
interface LibraryDao {
    @Query("SELECT * FROM library")
    fun getAll(): List<Library>?

    @Query("SELECT * FROM library WHERE id = :id")
    fun getById(id: String?): Library?

    @Query("SELECT * FROM library WHERE id IN (:idList)")
    fun getByLibraryListByIdList(idList: List<String>): List<Library>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(obj: List<Library>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: Library)
}