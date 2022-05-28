package com.jarvis.anime.repository.localDataSource.dao

import androidx.room.*
import com.jarvis.anime.model.media.Image
import com.jarvis.anime.model.media.ImageUpdateImageString
import com.jarvis.anime.model.media.ImageUpdateOrigin

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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(obj: Image)

    @Update(entity = Image::class)
    fun updateImageString(obj: ImageUpdateImageString): Int

    @Update(entity = Image::class)
    fun updateImage(obj: ImageUpdateOrigin): Int
}
