package com.jarvis.acg.ui.book

import androidx.recyclerview.widget.LinearLayoutManager
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseFragment
import com.jarvis.acg.databinding.FragmentBookSelectChapterBinding
import com.jarvis.acg.extension.ViewExtension.Companion.addClick
import com.jarvis.acg.model.*
import com.jarvis.acg.model.chapter.Chapter
import com.jarvis.acg.model.mangaChapter.MangaChapter
import com.jarvis.acg.ui.manga.select.MangaSelectChapterFragment
import com.jarvis.acg.ui.novel.select.NovelSelectChapterFragment
import com.jarvis.acg.util.GlideUtil.loadImage
import com.jarvis.acg.util.NavigationUtil.gotoMangaChapterFragment
import com.jarvis.acg.util.NavigationUtil.gotoNovelChapterFragment
import com.jarvis.acg.viewModel.MainViewModel
import com.jarvis.acg.viewModel.book.BookChapterViewModel

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
            gotoChapterPage(baseChapter.id)
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
            book?.let {
                updateIsEnd(book)
                mViewModel?.getLastSeenTitle(book)
            }
        }

        mViewModel?.work?.observe(viewLifecycleOwner) { work ->
            work?.let {
                updateTitle(work)
                updateThumbnail(work)
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

        mViewModel?.lastSeenChapterTitle?.observe(viewLifecycleOwner) { title ->
            title.let {
                getDataBinding().btnChapterNavigate.apply {
                    text = if (title != null) { "繼續 $title" } else { "開始" }
                    addClick({
                        if (title != null) {
                            mViewModel?.getBook()?.last_chapter_id?.let {
                                gotoChapterPage(it)
                            }
                        } else {
                            mViewModel?.getVolumeChapterList()?.let { list ->
                                val id = list.filterIsInstance<BaseChapter>().first().id
                                gotoChapterPage(id)
                            }
                        }
                    })
                }
            }
        }
    }

    private fun updateThumbnail(work: Work) {
        work.image_byte_list?.takeIf { !it.isNullOrEmpty() }?.get(0)?.let { imageString ->
            getDataBinding().imgThumbnail.loadImage(imageString)
        }
    }

    private fun gotoChapterPage(id: String) {
        if (this is NovelSelectChapterFragment) {
            gotoNovelChapterFragment(id)
        } else if (this is MangaSelectChapterFragment) {
            gotoMangaChapterFragment(id)
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