package com.jarvis.acg.ui.novel.select

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.google.android.flexbox.FlexboxLayoutManager
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseFragment
import com.jarvis.acg.databinding.FragmentNovelSelectChapterBinding
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
                mActivityViewModel?.fetchNovelVolumeList(novel)
            }
        }

        mActivityViewModel?.novelVolumeChapterList?.observe(viewLifecycleOwner) { list ->
            list?.let {
                volumeChapterAdapter?.clearData()
                volumeChapterAdapter?.addAllData(list)
            }
        }
    }

    override fun onDestroy() {
        mActivityViewModel?.getNovelVolumeChapterList()?.postValue(null)
        super.onDestroy()
    }
}