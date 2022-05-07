package com.jarvis.acg.repository.localDataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jarvis.acg.model.media.Image

@Dao
interface ImageDao {
    @Query("SELECT * FROM image")
    fun getAll(): List<Image>?

    @Query("SELECT * FROM image WHERE id = :id")
    fun getById(id: String?): Image?

    @Query("SELECT * FROM image WHERE id IN (:idList)")
    fun getByImageListByIdList(idList: List<String>): List<Image>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(obj: List<Image>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: Image)
}
