package com.jarvis.anime.repository.localDataSource.dao

import androidx.room.*
import com.jarvis.anime.model.BookUpdateLastSeen
import com.jarvis.anime.model.BookUpdateOrigin
import com.jarvis.anime.model.Novel

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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(obj: Novel)

    @Update(entity = Novel::class)
    fun updateLastSeen(obj: BookUpdateLastSeen): Int

    @Update(entity = Novel::class)
    fun update(obj: BookUpdateOrigin): Int
}