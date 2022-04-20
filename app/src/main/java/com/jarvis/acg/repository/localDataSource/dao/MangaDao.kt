package com.jarvis.acg.repository.localDataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jarvis.acg.model.Manga

@Dao
interface MangaDao {
    @Query("SELECT * FROM manga")
    fun getAll(): List<Manga>?

    @Query("SELECT * FROM manga WHERE id = :id")
    fun getById(id: String?): Manga?

    @Query("SELECT * FROM manga WHERE manga.work_id IN (:idList)")
    fun getByWorkIdList(idList: List<String>): List<Manga>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(obj: List<Manga>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: Manga)
}