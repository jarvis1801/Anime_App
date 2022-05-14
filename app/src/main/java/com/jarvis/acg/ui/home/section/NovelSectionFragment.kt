package com.jarvis.acg.ui.home.section

import com.jarvis.acg.model.Work
import com.jarvis.acg.util.NavigationUtil.gotoNovelSelectChapterFragment
import com.jarvis.acg.viewModel.home.NovelSectionViewModel

class NovelSectionFragment : BookSectionFragment<NovelSectionViewModel>() {
    override fun getViewModelClass(): Class<NovelSectionViewModel> = NovelSectionViewModel::class.java


    override fun subscribeViewModel() {
        super.subscribeViewModel()
    }

    override fun goToSelectChapterPage(work: Work) {
        gotoNovelSelectChapterFragment(work)
    }
}