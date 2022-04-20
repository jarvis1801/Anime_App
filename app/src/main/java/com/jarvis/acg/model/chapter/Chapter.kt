package com.jarvis.acg.model.chapter

import androidx.room.Entity
import androidx.room.Ignore
import com.jarvis.acg.model.BaseNameObject
import com.jarvis.acg.model.Translation

@Entity(tableName = "chapter")
data class Chapter(
    var sectionName: Translation? = null,
    var content: Translation? = null,
    var volume_id: String? = null,
    var order: Int? = null,
    var currentLine: Int? = 0,
    var isRead: Boolean? = false,
    @Ignore var nextChapterId: String = "",
    @Ignore var prevChapterId: String = ""
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