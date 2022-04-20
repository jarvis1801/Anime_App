package com.jarvis.acg.ui.novel.select

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.google.android.flexbox.FlexboxLayoutManager
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseFragment
import com.jarvis.acg.databinding.FragmentNovelSelectChapterBinding
import com.jarvis.acg.model.Author
import com.jarvis.acg.model.Novel
import com.jarvis.acg.model.Work
import com.jarvis.acg.util.NavigationUtil.gotoNovelChapterFragment
import com.jarvis.acg.viewModel.MainViewModel
import com.jarvis.acg.viewModel.novel.NovelSelectChapterViewModel

class NovelSelectChapterFragment : BaseFragment<FragmentNovelSelectChapterBinding, NovelSelectChapterViewModel, MainViewModel>() {

    private var volumeChapterAdapter: NovelVolumeChapterAdapter? = null

    override fun getArgs(): Bundle {
        val args: NovelSelectChapterFragmentArgs by navArgs()
        return args.toBundle()
    }

    override fun getLayoutId(): Int = R.layout.fragment_novel_select_chapter

    override fun getViewModelClass(): Class<NovelSelectChapterViewModel> = NovelSelectChapterViewModel::class.java

    override fun getActivityViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun initView() {
        volumeChapterAdapter = NovelVolumeChapterAdapter(requireContext()) { chapter ->
            gotoNovelChapterFragment(chapter)
        }

        getDataBinding().rvVolumeChapter.apply {
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
        mViewModel?.novel?.observe(viewLifecycleOwner) { novel ->
            novel?.let {
                updateIsEnd(novel)
                mActivityViewModel?.fetchNovelVolumeList(novel)
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

        mActivityViewModel?.novelVolumeChapterList?.observe(viewLifecycleOwner) { map ->
            map?.let {
                mActivityViewModel?.getNovelVolumeChapterList()?.let { list ->
                    volumeChapterAdapter?.clearData()
                    volumeChapterAdapter?.addAllData(list)
                }
            }
        }
    }

    private fun updateIsEnd(novel: Novel) {
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