package com.jarvis.anime.ui.home.section

import com.jarvis.anime.model.Work
import com.jarvis.anime.util.NavigationUtil.gotoMangaSelectChapterFragment
import com.jarvis.anime.viewModel.home.MangaSectionViewModel

class MangaSectionFragment : BookSectionFragment<MangaSectionViewModel>() {
    override fun getViewModelClass(): Class<MangaSectionViewModel> = MangaSectionViewModel::class.java

    override fun goToSelectChapterPage(work: Work) {
        gotoMangaSelectChapterFragment(work)
    }
}