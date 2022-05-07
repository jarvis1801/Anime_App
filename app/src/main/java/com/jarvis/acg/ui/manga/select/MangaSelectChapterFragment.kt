package com.jarvis.acg.ui.manga.select

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.jarvis.acg.model.Manga
import com.jarvis.acg.model.mangaChapter.MangaChapter
import com.jarvis.acg.ui.book.BookSelectChapterFragment
import com.jarvis.acg.viewModel.manga.MangaChapterViewModel

class MangaSelectChapterFragment : BookSelectChapterFragment<Manga, MangaChapter, MangaChapterViewModel>() {

    override fun getArgs(): Bundle {
        val args: MangaSelectChapterFragmentArgs by navArgs()
        return args.toBundle()
    }

    override fun getViewModelClass(): Class<MangaChapterViewModel> { return MangaChapterViewModel::class.java }


}