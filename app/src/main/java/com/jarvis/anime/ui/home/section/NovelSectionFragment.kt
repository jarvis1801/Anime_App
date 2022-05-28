package com.jarvis.anime.ui.home.section

import com.jarvis.anime.model.Work
import com.jarvis.anime.util.NavigationUtil.gotoNovelSelectChapterFragment
import com.jarvis.anime.viewModel.home.NovelSectionViewModel

class NovelSectionFragment : BookSectionFragment<NovelSectionViewModel>() {
    override fun getViewModelClass(): Class<NovelSectionViewModel> = NovelSectionViewModel::class.java


    override fun subscribeViewModel() {
        super.subscribeViewModel()
    }

    override fun goToSelectChapterPage(work: Work) {
        gotoNovelSelectChapterFragment(work)
    }
}