package com.jarvis.anime.util

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jarvis.anime.R
import com.jarvis.anime.model.Work
import com.jarvis.anime.ui.home.HomeFragmentDirections
import com.jarvis.anime.ui.manga.select.MangaSelectChapterFragmentDirections
import com.jarvis.anime.ui.novel.select.NovelSelectChapterFragmentDirections

object NavigationUtil {

    fun Fragment.gotoHomeFragment() {
        findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
    }

    fun Fragment.gotoNovelSelectChapterFragment(item: Work) {
        val novelId = item.book_id
        val action = HomeFragmentDirections.actionHomeFragmentToNovelSelectChapterFragment(novelId)
        this.findNavController().navigate(action)
    }

    fun Fragment.gotoMangaSelectChapterFragment(item: Work) {
        val mangaId = item.book_id
        val action = HomeFragmentDirections.actionHomeFragmentToMangaSelectChapterFragment(mangaId)
        this.findNavController().navigate(action)
    }

    fun Fragment.gotoNovelChapterFragment(chapterId: String) {
        val action = NovelSelectChapterFragmentDirections.actionNovelSelectChapterFragmentToNovelChapterFragment(chapterId)
        findNavController().navigate(action)
    }

    fun Fragment.gotoMangaChapterFragment(chapterId: String) {
        val action = MangaSelectChapterFragmentDirections.actionMangaSelectChapterFragmentToMangaChapterFragment(chapterId)
        findNavController().navigate(action)
    }

    fun Fragment.gotoNovelContentSettingFragment() {
        findNavController().navigate(R.id.action_novelChapterFragment_to_novelChapterContentSettingFragment)
    }
}