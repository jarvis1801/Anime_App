package com.jarvis.acg.ui.home.section

import com.jarvis.acg.model.Work
import com.jarvis.acg.util.NavigationUtil.gotoMangaSelectChapterFragment
import com.jarvis.acg.viewModel.home.MangaSectionViewModel

class MangaSectionFragment : BookSectionFragment<MangaSectionViewModel>() {
    override fun getViewModelClass(): Class<MangaSectionViewModel> = MangaSectionViewModel::class.java

    override fun goToSelectChapterPage(work: Work) {
        gotoMangaSelectChapterFragment(work)
    }
}