package com.jarvis.acg.base

import com.jarvis.acg.repository.Resource
import com.jarvis.acg.repository.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepository {
    protected suspend fun <A> performGet(databaseQuery: () -> A,
                                            networkCall: suspend () -> Resource<A>,
                                            saveCallResult: suspend (A) -> Unit) : Resource<A> = withContext(Dispatchers.IO) {

        var response : Resource<A>? = null
        if (true) {
            response = networkCall.invoke()
        }
        val statusCode = response?.statusCode
        when (response?.status) {
            Status.SUCCESS -> {
                val data = response.data!!
                saveCallResult(data)
                return@withContext Resource.success(data, statusCode)
            }
            Status.ERROR -> {
                val error = response.message
                val data = databaseQuery.invoke()
                return@withContext Resource.error(error ?: "", data, statusCode)
            }
            else -> {
                val error = response?.message
                val data = databaseQuery.invoke()
                return@withContext Resource.error(error ?: "", data, statusCode)
            }
        }
    }
}