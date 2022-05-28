package com.jarvis.anime.util

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtil {
    const val FORMAT_SERVER_TIME = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

    @JvmStatic
    fun String.convertDateToTimestamp(originFormat: String): Long? {
        val dateFormat = SimpleDateFormat(originFormat, Locale.CHINESE)
        return dateFormat.parse(this)?.let { Timestamp(it.time).time }
    }
}