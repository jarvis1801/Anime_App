package com.jarvis.acg.repository.localDataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jarvis.acg.model.Volume

@Dao
interface VolumeDao {

    @Query("SELECT * FROM volume")
    fun getAll(): List<Volume>?

    @Query("SELECT * FROM volume WHERE id = :id")
    fun getById(id: String?): Volume?

    @Query("SELECT * FROM volume WHERE id IN (:idList)")
    fun getByVolumeListByIdList(idList: List<String>): List<Volume>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(obj: List<Volume>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: Volume)
}