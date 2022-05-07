package com.jarvis.acg.repository.image

import com.jarvis.acg.base.BaseRepository
import com.jarvis.acg.extension.Extension.Companion.join
import com.jarvis.acg.extension.Extension.Companion.toArrayList
import com.jarvis.acg.repository.localDataSource.dao.ImageDao

class ImageRepository(
    val remoteDataSource: ImageRemoteDataSource,
    val imageDao: ImageDao
) : BaseRepository() {

    suspend fun getListByIdList(idList: ArrayList<String>) = performGet(
        databaseQuery = { imageDao.getByImageListByIdList(idList).toArrayList() },
        networkCall = { remoteDataSource.getListByIdList(idList.join("_")) },
        saveCallResult = { imageDao.insertAll(it) }
    )

    suspend fun getListByIdListFromDB(idList: ArrayList<String>) = imageDao.getByImageListByIdList(idList).toArrayList()
}
