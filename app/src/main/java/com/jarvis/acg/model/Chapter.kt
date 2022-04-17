package com.jarvis.acg.model

import androidx.room.Entity

@Entity(tableName = "chapter")
data class Chapter(
    var sectionName: Translation? = null,
    var content: Translation? = null,
    var volume_id: String? = null,
    var order: Int? = null,
) : BaseNameObject() {

    fun getTitle(): String {
        val sectionName = sectionName?.getValue()
        val name = name?.getValue()
        return if (!sectionName.isNullOrBlank() && !name.isNullOrBlank()) return "$sectionName - $name"
            else if (sectionName.isNullOrBlank() && !name.isNullOrBlank()) return name
            else ""
    }

    fun getChapterContent(): String { return content?.getValue() ?: "" }
}