package com.jarvis.acg.ui.book

import android.os.BaseBundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseFragment
import com.jarvis.acg.base.BaseMultiTypeAdapter
import com.jarvis.acg.databinding.FragmentBookSelectChapterBinding
import com.jarvis.acg.extension.Extension.Companion.toArrayList
import com.jarvis.acg.model.*
import com.jarvis.acg.model.chapter.Chapter
import com.jarvis.acg.model.mangaChapter.MangaChapter
import com.jarvis.acg.util.NavigationUtil.gotoMangaChapterFragment
import com.jarvis.acg.util.NavigationUtil.gotoNovelChapterFragment
import com.jarvis.acg.viewModel.MainViewModel
import com.jarvis.acg.viewModel.book.BookChapterViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

abstract class BookSelectChapterFragment<B: Book, C: BaseChapter, VM: BookChapterViewModel<B, C>> : BaseFragment<FragmentBookSelectChapterBinding, VM, MainViewModel>() {

    private var volumeChapterAdapter: BookVolumeChapterAdapter? = null

    override fun getLayoutId(): Int = R.layout.fragment_book_select_chapter

    override fun getActivityViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override var isChildViewModelShare: Boolean = true

    override fun subscribeViewModel() {
        super.subscribeViewModel()

        observeViewModel()
    }

    override fun initView() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        volumeChapterAdapter = BookVolumeChapterAdapter(requireContext()) { baseChapter ->
            if (baseChapter is Chapter) {
                gotoNovelChapterFragment(baseChapter)
            } else if (baseChapter is MangaChapter) {
                gotoMangaChapterFragment(baseChapter)
            }
        }


        getDataBinding().rvBookChapter.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = volumeChapterAdapter
        }
    }

    override fun initListener() {

    }

    override fun initStartEvent() {
        if (mViewModel?.getVolumeChapterList() == null) {
            arguments?.run {
                getString("bookId")?.let {
                    mViewModel?.fetchAllInfo(it)
                }
            }
        }
    }

    private fun observeViewModel() {
        mViewModel?.book?.observe(viewLifecycleOwner) { book ->
            book?.let { updateIsEnd(book) }
        }

        mViewModel?.work?.observe(viewLifecycleOwner) { work ->
            work?.let {
                updateTitle(work)
            }
        }

        mViewModel?.authorList?.observe(viewLifecycleOwner) { authorList ->
            authorList?.takeIf { it.isNotEmpty() }?.let {
                updateAuthor(authorList.first())
            }
        }

        mViewModel?.volumeChapterList?.observe(viewLifecycleOwner) { list ->
            list?.let {
                volumeChapterAdapter?.clearData()
                volumeChapterAdapter?.addAllData(list)
            }
        }
    }


    private fun updateIsEnd(novel: B) {
        novel.ended?.let { getDataBinding().tvIsEnd.text = if (novel.ended!!) "Ended" else "Not End Yet" }
    }

    private fun updateAuthor(author: Author) {
        getDataBinding().tvAuthor.text = author.getNameForLocale()
    }

    private fun updateTitle(work: Work) {
        getDataBinding().tvTitle.text = work.getNameForLocale()
    }

    override fun onDestroy() {
        mViewModel?.resetViewModel()
        super.onDestroy()
    }
}