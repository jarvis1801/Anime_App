package com.jarvis.anime.viewModel

import androidx.lifecycle.SavedStateHandle
import com.jarvis.anime.App
import com.jarvis.anime.repository.author.AuthorRemoteDataSource
import com.jarvis.anime.repository.author.AuthorRepository
import com.jarvis.anime.repository.chapter.ChapterRemoteDataSource
import com.jarvis.anime.repository.chapter.ChapterRepository
import com.jarvis.anime.repository.image.ImageRemoteDataSource
import com.jarvis.anime.repository.image.ImageRepository
import com.jarvis.anime.repository.library.LibraryRemoteDataSource
import com.jarvis.anime.repository.library.LibraryRepository
import com.jarvis.anime.repository.manga.MangaRemoteDataSource
import com.jarvis.anime.repository.manga.MangaRepository
import com.jarvis.anime.repository.mangaChapter.MangaChapterRemoteDataSource
import com.jarvis.anime.repository.mangaChapter.MangaChapterRepository
import com.jarvis.anime.repository.novel.NovelRepository
import com.jarvis.anime.repository.novel.NovelRemoteDataSource
import com.jarvis.anime.repository.painter.PainterRemoteDataSource
import com.jarvis.anime.repository.painter.PainterRepository
import com.jarvis.anime.repository.publishingHouse.PublishingHouseRemoteDataSource
import com.jarvis.anime.repository.publishingHouse.PublishingHouseRepository
import com.jarvis.anime.repository.volume.VolumeRemoteDataSource
import com.jarvis.anime.repository.volume.VolumeRepository
import com.jarvis.anime.repository.work.WorkRemoteDataSource
import com.jarvis.anime.repository.work.WorkRepository
import com.jarvis.anime.viewModel.home.HomeViewModel
import com.jarvis.anime.viewModel.home.MangaSectionViewModel
import com.jarvis.anime.viewModel.home.NovelSectionViewModel
import com.jarvis.anime.viewModel.manga.MangaChapterViewModel
import com.jarvis.anime.viewModel.novel.NovelChapterViewModel

object ViewModelBuilder {

    fun buildMainViewModel() : MainViewModel {
        return MainViewModel()
    }

    fun buildHomeViewModel() : HomeViewModel {
        return HomeViewModel()
    }

    fun buildNovelSectionViewModel(): NovelSectionViewModel {
        val novelRepository = NovelRepository(NovelRemoteDataSource(), App.database.novelDao())
        val workRepository = WorkRepository(WorkRemoteDataSource(), App.database.workDao())
        val painterRepository = PainterRepository(PainterRemoteDataSource(), App.database.painterDao())
        val libraryRepository = LibraryRepository(LibraryRemoteDataSource(), App.database.libraryDao())
        val publishingHouseRepository = PublishingHouseRepository(PublishingHouseRemoteDataSource(), App.database.publishingHouseDao())
        val authorRepository = AuthorRepository(AuthorRemoteDataSource(), App.database.authorDao())
        val volumeRepository = VolumeRepository(VolumeRemoteDataSource(), App.database.volumeDao())
        val chapterRepository = ChapterRepository(ChapterRemoteDataSource(), App.database.chapterDao())
        val imageRepository = ImageRepository(ImageRemoteDataSource(), App.database.imageDao())

        return NovelSectionViewModel(
            novelRepository,
            workRepository,
            painterRepository,
            libraryRepository,
            publishingHouseRepository,
            authorRepository,
            volumeRepository,
            chapterRepository,
            imageRepository
        )
    }

    fun buildMangaSectionViewModel(): MangaSectionViewModel {
        val mangaRepository = MangaRepository(MangaRemoteDataSource(), App.database.mangaDao())
        val workRepository = WorkRepository(WorkRemoteDataSource(), App.database.workDao())
        val painterRepository = PainterRepository(PainterRemoteDataSource(), App.database.painterDao())
        val libraryRepository = LibraryRepository(LibraryRemoteDataSource(), App.database.libraryDao())
        val publishingHouseRepository = PublishingHouseRepository(PublishingHouseRemoteDataSource(), App.database.publishingHouseDao())
        val authorRepository = AuthorRepository(AuthorRemoteDataSource(), App.database.authorDao())
        val volumeRepository = VolumeRepository(VolumeRemoteDataSource(), App.database.volumeDao())
        val mangaChapterRepository = MangaChapterRepository(MangaChapterRemoteDataSource(), App.database.mangaChapterDao())
        val imageRepository = ImageRepository(ImageRemoteDataSource(), App.database.imageDao())

        return MangaSectionViewModel(
            mangaRepository,
            workRepository,
            painterRepository,
            libraryRepository,
            publishingHouseRepository,
            authorRepository,
            volumeRepository,
            mangaChapterRepository,
            imageRepository
        )
    }

    fun buildNovelSelectChapterViewModel(handle: SavedStateHandle) : NovelChapterViewModel {
        val novelRepository = NovelRepository(NovelRemoteDataSource(), App.database.novelDao())
        val workRepository = WorkRepository(WorkRemoteDataSource(), App.database.workDao())
        val authorRepository = AuthorRepository(AuthorRemoteDataSource(), App.database.authorDao())
        val volumeRepository = VolumeRepository(VolumeRemoteDataSource(), App.database.volumeDao())
        val chapterRepository = ChapterRepository(ChapterRemoteDataSource(), App.database.chapterDao())
        val mangaChapterRepository = MangaChapterRepository(MangaChapterRemoteDataSource(), App.database.mangaChapterDao())
        val imageRepository = ImageRepository(ImageRemoteDataSource(), App.database.imageDao())

        return NovelChapterViewModel(
            handle,
            novelRepository,
            workRepository,
            authorRepository,
            volumeRepository,
            chapterRepository,
            mangaChapterRepository,
            imageRepository
        )
    }

    fun buildMangaSelectChapterViewModel(handle: SavedStateHandle) : MangaChapterViewModel {
        val mangaRepository = MangaRepository(MangaRemoteDataSource(), App.database.mangaDao())
        val workRepository = WorkRepository(WorkRemoteDataSource(), App.database.workDao())
        val authorRepository = AuthorRepository(AuthorRemoteDataSource(), App.database.authorDao())
        val volumeRepository = VolumeRepository(VolumeRemoteDataSource(), App.database.volumeDao())
        val chapterRepository = ChapterRepository(ChapterRemoteDataSource(), App.database.chapterDao())
        val mangaChapterRepository = MangaChapterRepository(MangaChapterRemoteDataSource(), App.database.mangaChapterDao())
        val imageRepository = ImageRepository(ImageRemoteDataSource(), App.database.imageDao())

        return MangaChapterViewModel(
            handle,
            mangaRepository,
            workRepository,
            authorRepository,
            volumeRepository,
            chapterRepository,
            mangaChapterRepository,
            imageRepository
        )
    }
}