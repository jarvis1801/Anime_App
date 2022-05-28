package com.jarvis.anime.repository.author

import com.jarvis.anime.base.BaseDataSource
import com.jarvis.anime.network.AuthorService
import com.jarvis.anime.network.RetrofitClient

class AuthorRemoteDataSource : BaseDataSource() {
    suspend fun getAuthorListByIdList(idList: String) = getResult {
        RetrofitClient()
            .getService<AuthorService>()
            .getAuthorListByIdList(idList)
    }
}