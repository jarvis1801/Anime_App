package com.jarvis.anime.ui.manga.select

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.jarvis.anime.model.Manga
import com.jarvis.anime.model.mangaChapter.MangaChapter
import com.jarvis.anime.ui.book.BookSelectChapterFragment
import com.jarvis.anime.viewModel.manga.MangaChapterViewModel

class MangaSelectChapterFragment : BookSelectChapterFragment<Manga, MangaChapter, MangaChapterViewModel>() {

    override fun getArgs(): Bundle {
        val args: MangaSelectChapterFragmentArgs by navArgs()
        return args.toBundle()
    }

    override fun getViewModelClass(): Class<MangaChapterViewModel> { return MangaChapterViewModel::class.java }


}