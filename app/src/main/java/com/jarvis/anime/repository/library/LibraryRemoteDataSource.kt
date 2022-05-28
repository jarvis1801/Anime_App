package com.jarvis.anime.repository.library

import com.jarvis.anime.base.BaseDataSource
import com.jarvis.anime.network.LibraryService
import com.jarvis.anime.network.RetrofitClient

class LibraryRemoteDataSource : BaseDataSource() {

    suspend fun getLibraryListByIdList(idList: String) = getResult {
        RetrofitClient()
            .getService<LibraryService>()
            .getLibraryListByIdList(idList)
    }
}