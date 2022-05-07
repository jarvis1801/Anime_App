package com.jarvis.acg.ui.novel.select

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.jarvis.acg.model.Novel
import com.jarvis.acg.model.chapter.Chapter
import com.jarvis.acg.ui.book.BookSelectChapterFragment
import com.jarvis.acg.viewModel.novel.NovelChapterViewModel

class NovelSelectChapterFragment : BookSelectChapterFragment<Novel, Chapter, NovelChapterViewModel>() {

    override var isChildViewModelShare: Boolean = true

    override fun getArgs(): Bundle {
        val args: NovelSelectChapterFragmentArgs by navArgs()
        return args.toBundle()
    }

    override fun getViewModelClass(): Class<NovelChapterViewModel> { return NovelChapterViewModel::class.java }
}