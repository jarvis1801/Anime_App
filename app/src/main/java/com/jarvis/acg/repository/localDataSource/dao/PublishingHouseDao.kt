package com.jarvis.acg.repository.localDataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jarvis.acg.model.PublishingHouse

@Dao
interface PublishingHouseDao {

    @Query("SELECT * FROM publishingHouse")
    fun getAll(): List<PublishingHouse>?

    @Query("SELECT * FROM publishingHouse WHERE id = :id")
    fun getById(id: String?): PublishingHouse?

    @Query("SELECT * FROM publishingHouse WHERE id IN (:idList)")
    fun getByPublishingHouseListByIdList(idList: List<String>): List<PublishingHouse>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(obj: List<PublishingHouse>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: PublishingHouse)
}