package com.jarvis.anime.repository.localDataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jarvis.anime.model.*
import com.jarvis.anime.model.chapter.Chapter
import com.jarvis.anime.model.mangaChapter.MangaChapter
import com.jarvis.anime.model.media.Image
import com.jarvis.anime.repository.localDataSource.dao.*

@Database(entities = [Novel::class, Work::class, Painter::class, Library::class, PublishingHouse::class,
    Author::class, Chapter::class, Volume::class, Manga::class, MangaChapter::class, Image::class], version = 1, exportSchema = false)
@TypeConverters(GenericDataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun novelDao(): NovelDao
    abstract fun mangaDao(): MangaDao

    abstract fun workDao(): WorkDao
    abstract fun painterDao(): PainterDao
    abstract fun libraryDao(): LibraryDao
    abstract fun publishingHouseDao(): PublishingHouseDao
    abstract fun authorDao(): AuthorDao
    abstract fun volumeDao(): VolumeDao
    abstract fun chapterDao(): ChapterDao
    abstract fun mangaChapterDao(): MangaChapterDao

    abstract fun imageDao(): ImageDao
//    abstract fun mediaDao(): MediaDao
}