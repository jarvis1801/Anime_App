package com.jarvis.acg.viewModel.novel

import androidx.lifecycle.SavedStateHandle
import com.jarvis.acg.model.Novel
import com.jarvis.acg.repository.author.AuthorRepository
import com.jarvis.acg.repository.novel.NovelRepository
import com.jarvis.acg.repository.work.WorkRepository
import com.jarvis.acg.viewModel.book.BookSelectChapterViewModel

class NovelSelectChapterViewModel(
    savedStateHandle: SavedStateHandle,
    novelRepository: NovelRepository,
    workRepository: WorkRepository,
    authorRepository: AuthorRepository
) : BookSelectChapterViewModel<Novel>(savedStateHandle, novelRepository, workRepository, authorRepository)