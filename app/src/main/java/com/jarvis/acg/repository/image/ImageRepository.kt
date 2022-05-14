package com.jarvis.acg.repository.image

import com.jarvis.acg.base.BaseRepository
import com.jarvis.acg.extension.Extension.Companion.join
import com.jarvis.acg.extension.Extension.Companion.toArrayList
import com.jarvis.acg.model.chapter.ChapterUpdate
import com.jarvis.acg.model.media.ImageUpdateImageString
import com.jarvis.acg.model.media.ImageUpdateOrigin
import com.jarvis.acg.repository.localDataSource.dao.ImageDao

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
