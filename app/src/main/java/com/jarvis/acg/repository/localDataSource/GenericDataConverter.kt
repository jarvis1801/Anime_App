package com.jarvis.acg.repository.localDataSource

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.jarvis.acg.extension.Extension.Companion.fromJson
import com.jarvis.acg.model.*

class GenericDataConverter {
    @TypeConverter
    fun toAuthorObj(string: String?): Author? {
        return string?.let { Gson().fromJson(string) }
    }

    @TypeConverter
    fun fromNovelObj(obj: Author?): String? {
        return obj?.let { Gson().toJson(obj) }
    }

    @TypeConverter
    fun toLibraryObj(string: String?): Library? {
        return string?.let { Gson().fromJson(string) }
    }

    @TypeConverter
    fun fromLibraryObj(obj: Library?): String? {
        return obj?.let { Gson().toJson(obj) }
    }

    @TypeConverter
    fun toNovelObj(string: String?): Novel? {
        return string?.let { Gson().fromJson(string) }
    }

    @TypeConverter
    fun fromNovelObj(obj: Novel?): String? {
        return obj?.let { Gson().toJson(obj) }
    }

    @TypeConverter
    fun toPainterObj(string: String?): Painter? {
        return string?.let { Gson().fromJson(string) }
    }

    @TypeConverter
    fun fromPainterObj(obj: Painter?): String? {
        return obj?.let { Gson().toJson(obj) }
    }

    @TypeConverter
    fun toPersonObj(string: String?): Person? {
        return string?.let { Gson().fromJson(string) }
    }

    @TypeConverter
    fun fromPersonObj(obj: Person?): String? {
        return obj?.let { Gson().toJson(obj) }
    }

    @TypeConverter
    fun toPublishingHouseObj(string: String?): PublishingHouse? {
        return string?.let { Gson().fromJson(string) }
    }

    @TypeConverter
    fun fromPublishingHouseObj(obj: PublishingHouse?): String? {
        return obj?.let { Gson().toJson(obj) }
    }

    @TypeConverter
    fun toTranslationObj(string: String?): Translation? {
        return string?.let { Gson().fromJson(string) }
    }

    @TypeConverter
    fun fromTranslationObj(obj: Translation?): String? {
        return obj?.let { Gson().toJson(obj) }
    }

    @TypeConverter
    fun toWorkObj(string: String?): Work? {
        return string?.let { Gson().fromJson(string) }
    }

    @TypeConverter
    fun fromWorkObj(obj: Work?): String? {
        return obj?.let { Gson().toJson(obj) }
    }

    @TypeConverter
    fun toChapterObj(string: String?): Chapter? {
        return string?.let { Gson().fromJson(string) }
    }

    @TypeConverter
    fun fromChapterObj(obj: Chapter?): String? {
        return obj?.let { Gson().toJson(obj) }
    }

    @TypeConverter
    fun toStringListObj(string: String?): ArrayList<String>? {
        return string?.let { Gson().fromJson(string) }
    }

    @TypeConverter
    fun fromStringListObj(obj: ArrayList<String>?): String? {
        return obj?.let { Gson().toJson(obj) }
    }
}