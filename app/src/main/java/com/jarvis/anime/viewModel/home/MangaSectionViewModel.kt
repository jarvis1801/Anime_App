package com.jarvis.anime.viewModel.home

import com.jarvis.anime.model.Manga
import com.jarvis.anime.model.mangaChapter.MangaChapter
import com.jarvis.anime.repository.author.AuthorRepository
import com.jarvis.anime.repository.book.BookRepository
import com.jarvis.anime.repository.chapter.BaseChapterRepository
import com.jarvis.anime.repository.image.ImageRepository
import com.jarvis.anime.repository.library.LibraryRepository
import com.jarvis.anime.repository.painter.PainterRepository
import com.jarvis.anime.repository.publishingHouse.PublishingHouseRepository
import com.jarvis.anime.repository.volume.VolumeRepository
import com.jarvis.anime.repository.work.WorkRepository

class MangaSectionViewModel(
    private val bookRepository: BookRepository<Manga>,
    private val workRepository: WorkRepository,
    private val painterRepository: PainterRepository,
    private val libraryRepository: LibraryRepository,
    private val publishingHouseRepository: PublishingHouseRepository,
    private val authorRepository: AuthorRepository,
    private val volumeRepository: VolumeRepository,
    private val chapterRepository: BaseChapterRepository<MangaChapter>,
    private val imageRepository: ImageRepository
) : BookSectionViewModel<Manga, MangaChapter>(
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