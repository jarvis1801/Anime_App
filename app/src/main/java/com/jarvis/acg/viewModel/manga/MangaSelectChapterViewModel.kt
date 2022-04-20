package com.jarvis.acg.viewModel.manga

import androidx.lifecycle.SavedStateHandle
import com.jarvis.acg.model.Manga
import com.jarvis.acg.repository.author.AuthorRepository
import com.jarvis.acg.repository.manga.MangaRepository
import com.jarvis.acg.repository.work.WorkRepository
import com.jarvis.acg.viewModel.book.BookSelectChapterViewModel

class MangaSelectChapterViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val mangaRepository: MangaRepository,
    private val workRepository: WorkRepository,
    private val authorRepository: AuthorRepository
) : BookSelectChapterViewModel<Manga>(
    savedStateHandle,
    mangaRepository,
    workRepository,
    authorRepository,
)