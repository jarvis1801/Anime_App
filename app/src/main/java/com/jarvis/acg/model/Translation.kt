package com.jarvis.acg.model

data class Translation(
    var tc: String? = null,
    var en: String? = null,
    var jp: String? = null,
    var ro: String? = null
) {
    fun getValue(): String {
        // return locale
        return tc ?: ""
    }
}