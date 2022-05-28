package com.jarvis.anime.repository.localDataSource.dao

import androidx.room.*
import com.jarvis.anime.model.BookUpdateLastSeen
import com.jarvis.anime.model.BookUpdateOrigin
import com.jarvis.anime.model.Manga

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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(obj: Manga)

    @Update(entity = Manga::class)
    fun updateLastSeen(obj: BookUpdateLastSeen): Int

    @Update(entity = Manga::class)
    fun update(obj: BookUpdateOrigin): Int
}