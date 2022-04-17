package com.jarvis.acg.repository.library

import com.jarvis.acg.base.BaseDataSource
import com.jarvis.acg.network.LibraryService
import com.jarvis.acg.network.RetrofitClient

class LibraryRemoteDataSource : BaseDataSource() {

    suspend fun getLibraryListByIdList(idList: String) = getResult {
        RetrofitClient()
            .getService<LibraryService>()
            .getLibraryListByIdList(idList)
    }
}