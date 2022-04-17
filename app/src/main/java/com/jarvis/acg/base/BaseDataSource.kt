package com.jarvis.acg.base

import com.jarvis.acg.repository.Resource
import retrofit2.Response

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        return try {
            val response = call()
            val statusCode = response.code()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Resource.success(body, statusCode)
                }
            }
            error(response.message(), null, statusCode = statusCode)
        } catch (e: Exception) {
            error(e.message ?: e.toString(), e.javaClass.canonicalName)
        }
    }

    private fun <T> error(message: String, canonicalName: String?, statusCode: Int? = null): Resource<T> {
        canonicalName?.let {
            if (it.contains("SocketTimeoutException")) {
                return Resource.error("Network call has failed for a following reason: Cannot access", statusCode = statusCode)
            }
        }
        return Resource.error("Network call has failed for a following reason: $message", statusCode = statusCode)
    }
}