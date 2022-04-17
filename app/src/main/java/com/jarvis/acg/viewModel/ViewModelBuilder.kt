package com.jarvis.acg.viewModel

import androidx.lifecycle.SavedStateHandle
import com.jarvis.acg.App
import com.jarvis.acg.repository.author.AuthorRemoteDataSource
import com.jarvis.acg.repository.author.AuthorRepository
import com.jarvis.acg.repository.chapter.ChapterRemoteDataSource
import com.jarvis.acg.repository.chapter.ChapterRepository
import com.jarvis.acg.repository.library.LibraryRemoteDataSource
import com.jarvis.acg.repository.library.LibraryRepository
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
import com.jarvis.acg.viewModel.novel.NovelChapterViewModel
import com.jarvis.acg.viewModel.novel.NovelSelectChapterViewModel

object ViewModelBuilder {

    fun buildMainViewModel() : MainViewModel {
        val novelRepository = NovelRepository(NovelRemoteDataSource(), App.database.novelDao())
        val workRepository = WorkRepository(WorkRemoteDataSource(), App.database.workDao())
        val painterRepository = PainterRepository(PainterRemoteDataSource(), App.database.painterDao())
        val libraryRepository = LibraryRepository(LibraryRemoteDataSource(), App.database.libraryDao())
        val publishingHouseRepository = PublishingHouseRepository(PublishingHouseRemoteDataSource(), App.database.publishingHouseDao())
        val authorRepository = AuthorRepository(AuthorRemoteDataSource(), App.database.authorDao())
        val volumeRepository = VolumeRepository(VolumeRemoteDataSource(), App.database.volumeDao())
        val chapterRepository = ChapterRepository(ChapterRemoteDataSource(), App.database.chapterDao())

        return MainViewModel(
            novelRepository,
            workRepository,
            painterRepository,
            libraryRepository,
            publishingHouseRepository,
            authorRepository,
            volumeRepository,
            chapterRepository
        )
    }

    fun buildHomeViewModel() : HomeViewModel {
        return HomeViewModel()
    }

    fun buildNovelSelectChapterViewModel(handle: SavedStateHandle) : NovelSelectChapterViewModel {
        val novelRepository = NovelRepository(NovelRemoteDataSource(), App.database.novelDao())
        val workRepository = WorkRepository(WorkRemoteDataSource(), App.database.workDao())
        val authorRepository = AuthorRepository(AuthorRemoteDataSource(), App.database.authorDao())

        return NovelSelectChapterViewModel(
            handle,
            novelRepository,
            workRepository,
            authorRepository
        )
    }

    fun buildNovelChapterViewModel(handle: SavedStateHandle) : NovelChapterViewModel {
        val chapterRepository = ChapterRepository(ChapterRemoteDataSource(), App.database.chapterDao())
        return NovelChapterViewModel(handle, chapterRepository)
    }
}