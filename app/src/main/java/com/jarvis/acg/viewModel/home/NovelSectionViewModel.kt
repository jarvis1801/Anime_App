package com.jarvis.acg.viewModel.home

import com.jarvis.acg.model.Manga
import com.jarvis.acg.model.Novel
import com.jarvis.acg.model.chapter.Chapter
import com.jarvis.acg.model.mangaChapter.MangaChapter
import com.jarvis.acg.repository.author.AuthorRepository
import com.jarvis.acg.repository.book.BookRepository
import com.jarvis.acg.repository.chapter.BaseChapterRepository
import com.jarvis.acg.repository.image.ImageRepository
import com.jarvis.acg.repository.library.LibraryRepository
import com.jarvis.acg.repository.painter.PainterRepository
import com.jarvis.acg.repository.publishingHouse.PublishingHouseRepository
import com.jarvis.acg.repository.volume.VolumeRepository
import com.jarvis.acg.repository.work.WorkRepository

class NovelSectionViewModel(
    private val bookRepository: BookRepository<Novel>,
    private val workRepository: WorkRepository,
    private val painterRepository: PainterRepository,
    private val libraryRepository: LibraryRepository,
    private val publishingHouseRepository: PublishingHouseRepository,
    private val authorRepository: AuthorRepository,
    private val volumeRepository: VolumeRepository,
    private val chapterRepository: BaseChapterRepository<Chapter>,
    private val imageRepository: ImageRepository
) : BookSectionViewModel<Novel, Chapter>(
    bookRepository,
    workRepository,
    painterRepository,
    libraryRepository,
    publishingHouseRepository,
    authorRepository,
    volumeRepository,
    chapterRepository,
    imageRepository
) {
}