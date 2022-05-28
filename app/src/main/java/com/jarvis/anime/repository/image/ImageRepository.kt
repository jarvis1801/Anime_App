package com.jarvis.anime.repository.image

import com.jarvis.anime.base.BaseRepository
import com.jarvis.anime.extension.Extension.Companion.join
import com.jarvis.anime.extension.Extension.Companion.toArrayList
import com.jarvis.anime.model.media.ImageUpdateImageString
import com.jarvis.anime.model.media.ImageUpdateOrigin
import com.jarvis.anime.repository.localDataSource.dao.ImageDao

class ImageRepository(
    val remoteDataSource: ImageRemoteDataSource,
    val imageDao: ImageDao
) : BaseRepository() {

    suspend fun getListByIdList(idList: ArrayList<String>) = performGet(
        databaseQuery = { imageDao.getByImageListByIdList(idList).toArrayList() },
        networkCall = { remoteDataSource.getListByIdList(idList.join("_")) },
        saveCallResult = {
            it.forEach {
                imageDao.insertIgnore(it)
                imageDao.updateImage(ImageUpdateOrigin(it))
            }
        }
    )

    suspend fun getListByIdListFromDB(idList: ArrayList<String>) = imageDao.getByImageListByIdList(idList).toArrayList()

    fun updateImageString(obj: ImageUpdateImageString) = imageDao.updateImageString(obj)
}
