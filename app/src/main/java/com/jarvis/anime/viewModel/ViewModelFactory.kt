package com.jarvis.anime.viewModel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.jarvis.anime.ui.splash.SplashViewModel
import com.jarvis.anime.viewModel.home.HomeViewModel
import com.jarvis.anime.viewModel.home.MangaSectionViewModel
import com.jarvis.anime.viewModel.home.NovelSectionViewModel
import com.jarvis.anime.viewModel.manga.MangaChapterViewModel
import com.jarvis.anime.viewModel.novel.NovelChapterViewModel

class ViewModelFactory(owner: SavedStateRegistryOwner, defaultArgs: Bundle? = Bundle()) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        return with(modelClass) {

            when {
                isAssignableFrom(MainViewModel::class.java) -> ViewModelBuilder.buildMainViewModel()
                isAssignableFrom(SplashViewModel::class.java) -> SplashViewModel()
                isAssignableFrom(HomeViewModel::class.java) -> ViewModelBuilder.buildHomeViewModel()

                isAssignableFrom(NovelSectionViewModel::class.java) -> ViewModelBuilder.buildNovelSectionViewModel()
                isAssignableFrom(MangaSectionViewModel::class.java) -> ViewModelBuilder.buildMangaSectionViewModel()

                isAssignableFrom(NovelChapterViewModel::class.java) -> ViewModelBuilder.buildNovelSelectChapterViewModel(handle)

                isAssignableFrom(MangaChapterViewModel::class.java) -> ViewModelBuilder.buildMangaSelectChapterViewModel(handle)

                isAssignableFrom(EmptyViewModel::class.java) -> EmptyViewModel()
                else ->
                    throw IllegalArgumentException(
                        "Unknown ViewModel class: ${modelClass.name}"
                    )
            }

        } as T
    }

}

