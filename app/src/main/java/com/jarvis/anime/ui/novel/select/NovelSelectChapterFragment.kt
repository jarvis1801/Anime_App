package com.jarvis.anime.ui.novel.select

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.jarvis.anime.model.Novel
import com.jarvis.anime.model.chapter.Chapter
import com.jarvis.anime.ui.book.BookSelectChapterFragment
import com.jarvis.anime.viewModel.novel.NovelChapterViewModel

class NovelSelectChapterFragment : BookSelectChapterFragment<Novel, Chapter, NovelChapterViewModel>() {

    override var isChildViewModelShare: Boolean = true

    override fun getArgs(): Bundle {
        val args: NovelSelectChapterFragmentArgs by navArgs()
        return args.toBundle()
    }

    override fun getViewModelClass(): Class<NovelChapterViewModel> { return NovelChapterViewModel::class.java }
}