package com.jarvis.anime.model.chapter

import androidx.room.Entity
import com.jarvis.anime.model.BaseChapter
import com.jarvis.anime.model.Translation

@Entity(tableName = "chapter")
data class Chapter(
    var content: Translation? = null,
    var currentLine: Int? = 0
) : BaseChapter() {

    fun getTitle(): String {
        val sectionName = sectionName?.getValue()
        val name = name?.getValue()
        return if (!sectionName.isNullOrBlank() && !name.isNullOrBlank()) return "$sectionName - $name"
            else if (sectionName.isNullOrBlank() && !name.isNullOrBlank()) return name
            else ""
    }

    fun getChapterContent(): String { return content?.getValue() ?: "" }
}