package com.jarvis.anime.viewModel.home

import com.jarvis.anime.model.Novel
import com.jarvis.anime.model.chapter.Chapter
import com.jarvis.anime.repository.author.AuthorRepository
import com.jarvis.anime.repository.book.BookRepository
import com.jarvis.anime.repository.chapter.BaseChapterRepository
import com.jarvis.anime.repository.image.ImageRepository
import com.jarvis.anime.repository.library.LibraryRepository
import com.jarvis.anime.repository.painter.PainterRepository
import com.jarvis.anime.repository.publishingHouse.PublishingHouseRepository
import com.jarvis.anime.repository.volume.VolumeRepository
import com.jarvis.anime.repository.work.WorkRepository

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