package com.jarvis.acg.repository.localDataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jarvis.acg.model.Author

@Dao
interface AuthorDao {
    @Query("SELECT * FROM author")
    fun getAll(): List<Author>?

    @Query("SELECT * FROM author WHERE id = :id")
    fun getById(id: String?): Author?

    @Query("SELECT * FROM author WHERE id IN (:idList)")
    fun getByAuthorListByIdList(idList: List<String>): List<Author>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(obj: List<Author>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: Author)
}