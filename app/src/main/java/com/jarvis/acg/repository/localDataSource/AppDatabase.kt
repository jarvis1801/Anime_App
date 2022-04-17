package com.jarvis.acg.repository.localDataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jarvis.acg.model.*
import com.jarvis.acg.repository.localDataSource.dao.*

@Database(entities = [Novel::class, Work::class, Painter::class, Library::class, PublishingHouse::class,
    Author::class, Chapter::class, Volume::class], version = 1)
@TypeConverters(GenericDataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun novelDao(): NovelDao
    abstract fun workDao(): WorkDao
    abstract fun painterDao(): PainterDao
    abstract fun libraryDao(): LibraryDao
    abstract fun publishingHouseDao(): PublishingHouseDao
    abstract fun authorDao(): AuthorDao
    abstract fun volumeDao(): VolumeDao
    abstract fun chapterDao(): ChapterDao
//    abstract fun mediaDao(): MediaDao
}