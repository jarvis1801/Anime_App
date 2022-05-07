package com.jarvis.acg.util

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jarvis.acg.R
import com.jarvis.acg.model.Work
import com.jarvis.acg.model.chapter.Chapter
import com.jarvis.acg.model.mangaChapter.MangaChapter
import com.jarvis.acg.ui.home.HomeFragmentDirections
import com.jarvis.acg.ui.manga.select.MangaSelectChapterFragmentDirections
import com.jarvis.acg.ui.novel.select.NovelSelectChapterFragmentDirections

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

    fun Fragment.gotoNovelChapterFragment(chapter: Chapter) {
        val chapterId = chapter.id
        val action = NovelSelectChapterFragmentDirections.actionNovelSelectChapterFragmentToNovelChapterFragment(chapterId)
        findNavController().navigate(action)
    }

    fun Fragment.gotoMangaChapterFragment(mangaChapter: MangaChapter) {
        val chapterId = mangaChapter.id
        val action = MangaSelectChapterFragmentDirections.actionMangaSelectChapterFragmentToMangaChapterFragment(chapterId)
        findNavController().navigate(action)
    }
}