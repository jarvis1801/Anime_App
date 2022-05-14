package com.jarvis.acg.ui.home.section

import androidx.recyclerview.widget.GridLayoutManager
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseFragment
import com.jarvis.acg.databinding.FragmentBookSectionBinding
import com.jarvis.acg.model.Work
import com.jarvis.acg.ui.home.section.adapter.BookSectionAdapter
import com.jarvis.acg.util.NavigationUtil.gotoMangaSelectChapterFragment
import com.jarvis.acg.util.NavigationUtil.gotoNovelSelectChapterFragment
import com.jarvis.acg.viewModel.MainViewModel
import com.jarvis.acg.viewModel.home.BookSectionViewModel

abstract class BookSectionFragment<VM: BookSectionViewModel<*, *>> : BaseFragment<FragmentBookSectionBinding, VM, MainViewModel>() {
    private lateinit var sectionAdapter: BookSectionAdapter

    override fun getLayoutId(): Int { return R.layout.fragment_book_section }

    override fun getActivityViewModelClass(): Class<MainViewModel> { return MainViewModel::class.java }

    override fun subscribeViewModel() {
        super.subscribeViewModel()
        mViewModel?.workList?.observe(viewLifecycleOwner) { list ->
            mViewModel?.requestApiFinished()
            list?.let { updateWorkList(list) }
        }
    }

    override fun initView() {
        sectionAdapter = BookSectionAdapter(requireContext()) { work ->
            goToSelectChapterPage(work)
        }

        getDataBinding().rvWork.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = sectionAdapter
        }
    }

    override fun initListener() {
    }

    override fun initStartEvent() {
        mViewModel?.fetchBook()
    }

    private fun updateWorkList(workList: ArrayList<Work>) {
        val resultList = arrayListOf<Any>().apply { addAll(workList) }
        sectionAdapter.clearData()
        sectionAdapter.addAllData(resultList)
    }

    abstract fun goToSelectChapterPage(work: Work)
}