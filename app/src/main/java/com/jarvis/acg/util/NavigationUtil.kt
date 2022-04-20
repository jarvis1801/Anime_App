package com.jarvis.acg.util

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.jarvis.acg.R
import com.jarvis.acg.model.chapter.Chapter
import com.jarvis.acg.model.Work
import com.jarvis.acg.ui.home.HomeFragmentDirections
import com.jarvis.acg.ui.novel.select.NovelSelectChapterFragmentDirections

object NavigationUtil {

    fun View.gotoNovelSelectChapterFragment(item: Work) {
        val novelId = item.novel_id
        val action = HomeFragmentDirections.actionHomeFragmentToNovelSelectChapterFragment(novelId)
        this.findNavController().navigate(action)
    }

    fun Fragment.gotoHomeFragment() {
        findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
    }

    fun Fragment.gotoNovelChapterFragment(chapter: Chapter) {
        val chapterId = chapter.id
        val action = NovelSelectChapterFragmentDirections.actionNovelSelectChapterFragmentToNovelChapterFragment(chapterId)
        findNavController().navigate(action)
    }
}