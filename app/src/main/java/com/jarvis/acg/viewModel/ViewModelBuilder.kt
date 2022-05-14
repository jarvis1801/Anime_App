package com.jarvis.acg.viewModel

import androidx.lifecycle.SavedStateHandle
import com.jarvis.acg.App
import com.jarvis.acg.repository.author.AuthorRemoteDataSource
import com.jarvis.acg.repository.author.AuthorRepository
import com.jarvis.acg.repository.chapter.ChapterRemoteDataSource
import com.jarvis.acg.repository.chapter.ChapterRepository
import com.jarvis.acg.repository.image.ImageRemoteDataSource
import com.jarvis.acg.repository.image.ImageRepository
import com.jarvis.acg.repository.library.LibraryRemoteDataSource
import com.jarvis.acg.repository.library.LibraryRepository
import com.jarvis.acg.repository.manga.MangaRemoteDataSource
import com.jarvis.acg.repository.manga.MangaRepository
import com.jarvis.acg.repository.mangaChapter.MangaChapterRemoteDataSource
import com.jarvis.acg.repository.mangaChapter.MangaChapterRepository
import com.jarvis.acg.repository.novel.NovelRepository
import com.jarvis.acg.repository.novel.NovelRemoteDataSource
import com.jarvis.acg.repository.painter.PainterRemoteDataSource
import com.jarvis.acg.repository.painter.PainterRepository
import com.jarvis.acg.repository.publishingHouse.PublishingHouseRemoteDataSource
import com.jarvis.acg.repository.publishingHouse.PublishingHouseRepository
import com.jarvis.acg.repository.volume.VolumeRemoteDataSource
import com.jarvis.acg.repository.volume.VolumeRepository
import com.jarvis.acg.repository.work.WorkRemoteDataSource
import com.jarvis.acg.repository.work.WorkRepository
import com.jarvis.acg.viewModel.home.HomeViewModel
import com.jarvis.acg.viewModel.home.MangaSectionViewModel
import com.jarvis.acg.viewModel.home.NovelSectionViewModel
import com.jarvis.acg.viewModel.manga.MangaChapterViewModel
import com.jarvis.acg.viewModel.novel.NovelChapterViewModel

object ViewModelBuilder {

    fun buildMainViewModel() : MainViewModel {
        val novelRepository = NovelRepository(NovelRemoteDataSource(), App.database.novelDao())
        val mangaRepository = MangaRepository(MangaRemoteDataSource(), App.database.mangaDao())
        val workRepository = WorkRepository(WorkRemoteDataSource(), App.database.workDao())
        val painterRepository = PainterRepository(PainterRemoteDataSource(), App.database.painterDao())
        val libraryRepository = LibraryRepository(LibraryRemoteDataSource(), App.database.libraryDao())
        val publishingHouseRepository = PublishingHouseRepository(PublishingHouseRemoteDataSource(), App.database.publishingHouseDao())
        val authorRepository = AuthorRepository(AuthorRemoteDataSource(), App.database.authorDao())
        val volumeRepository = VolumeRepository(VolumeRemoteDataSource(), App.database.volumeDao())
        val chapterRepository = ChapterRepository(ChapterRemoteDataSource(), App.database.chapterDao())
        val mangaChapterRepository = MangaChapterRepository(MangaChapterRemoteDataSource(), App.database.mangaChapterDao())
        val imageRepository = ImageRepository(ImageRemoteDataSource(), App.database.imageDao())

        return MainViewModel(
            novelRepository,
            mangaRepository,
            workRepository,
            painterRepository,
            libraryRepository,
            publishingHouseRepository,
            authorRepository,
            volumeRepository,
            chapterRepository,
            mangaChapterRepository,
            imageRepository
        )
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

        return NovelChapterViewModel(
            handle,
            novelRepository,
            workRepository,
            authorRepository,
            volumeRepository,
            chapterRepository,
            mangaChapterRepository
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