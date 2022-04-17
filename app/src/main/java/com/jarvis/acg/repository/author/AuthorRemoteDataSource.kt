package com.jarvis.acg.repository.author

import com.jarvis.acg.base.BaseDataSource
import com.jarvis.acg.network.AuthorService
import com.jarvis.acg.network.RetrofitClient

class AuthorRemoteDataSource : BaseDataSource() {
    suspend fun getAuthorListByIdList(idList: String) = getResult {
        RetrofitClient()
            .getService<AuthorService>()
            .getAuthorListByIdList(idList)
    }
}