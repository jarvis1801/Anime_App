package com.jarvis.anime.repository.localDataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jarvis.anime.model.Painter

@Dao
interface PainterDao {
    @Query("SELECT * FROM painter")
    fun getAll(): List<Painter>?

    @Query("SELECT * FROM painter WHERE id = :id")
    fun getById(id: String?): Painter?

    @Query("SELECT * FROM painter WHERE id IN (:idList)")
    fun getByPainterListByIdList(idList: List<String>): List<Painter>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(obj: List<Painter>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: Painter)
}