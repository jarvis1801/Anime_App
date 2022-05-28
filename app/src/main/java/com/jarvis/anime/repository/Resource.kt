package com.jarvis.anime.repository

data class Resource<T>(val status: Status, val data: T?, val message: String?, val statusCode: Int? = null) {
    companion object {
        fun <T> success(data: T, statusCode: Int? = null): Resource<T> =
            Resource(status = Status.SUCCESS, data = data, message = null, statusCode = statusCode)

        fun <T> error(message: String, data: T? = null, statusCode: Int? = null): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message, statusCode = statusCode)

//        fun <T> loading(data: T? = null): Resource<T> =
//            Resource(status = Status.LOADING, data = data, message = null)
    }
}


enum class Status {
    SUCCESS,
    ERROR,
//    LOADING
}