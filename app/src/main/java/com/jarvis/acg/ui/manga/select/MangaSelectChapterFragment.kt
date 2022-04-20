package com.jarvis.acg.ui.manga.select

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.jarvis.acg.model.Manga
import com.jarvis.acg.ui.book.BookSelectChapterFragment
import com.jarvis.acg.viewModel.manga.MangaSelectChapterViewModel

class MangaSelectChapterFragment : BookSelectChapterFragment<Manga, MangaSelectChapterViewModel>() {

    override fun getArgs(): Bundle {
        val args: MangaSelectChapterFragmentArgs by navArgs()
        return args.toBundle()
    }

    override fun getViewModelClass(): Class<MangaSelectChapterViewModel> { return MangaSelectChapterViewModel::class.java }
}