package com.jarvis.acg.ui.book

import com.google.android.flexbox.FlexboxLayoutManager
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseFragment
import com.jarvis.acg.databinding.FragmentBookSelectChapterBinding
import com.jarvis.acg.model.Author
import com.jarvis.acg.model.Book
import com.jarvis.acg.model.Novel
import com.jarvis.acg.model.Work
import com.jarvis.acg.ui.novel.select.NovelSelectChapterFragment
import com.jarvis.acg.util.NavigationUtil.gotoNovelChapterFragment
import com.jarvis.acg.viewModel.MainViewModel
import com.jarvis.acg.viewModel.book.BookSelectChapterViewModel

abstract class BookSelectChapterFragment<B: Book, VM: BookSelectChapterViewModel<B>> : BaseFragment<FragmentBookSelectChapterBinding, VM, MainViewModel>() {

    private var volumeChapterAdapter: BookVolumeChapterAdapter? = null

    override fun getLayoutId(): Int = R.layout.fragment_book_select_chapter

    override fun getActivityViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun initView() {
        volumeChapterAdapter = BookVolumeChapterAdapter(requireContext()) { chapter ->
            gotoNovelChapterFragment(chapter)
        }

        getDataBinding().rvBookChapter.apply {
            layoutManager = FlexboxLayoutManager(requireContext())
            adapter = volumeChapterAdapter
            setHasFixedSize(true)
        }
    }

    override fun initListener() {

    }

    override fun initStartEvent() {
        observeViewModel()
    }

    private fun observeViewModel() {
        mViewModel?.book?.observe(viewLifecycleOwner) { book ->
            book?.let {
                updateIsEnd(book)
                if (book is Novel) {
                    mActivityViewModel?.fetchNovelVolumeList(book)
                }
            }
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

        if (this is NovelSelectChapterFragment) {
            mActivityViewModel?.novelVolumeChapterList?.observe(viewLifecycleOwner) { map ->
                map?.let {
                    mActivityViewModel?.getNovelVolumeChapterList()?.let { list ->
                        volumeChapterAdapter?.clearData()
                        volumeChapterAdapter?.addAllData(list)
                    }
                }
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
        mActivityViewModel?.setNovelVolumeChapterList(null)
        super.onDestroy()
    }
}