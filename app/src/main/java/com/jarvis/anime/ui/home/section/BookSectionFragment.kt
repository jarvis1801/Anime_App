package com.jarvis.anime.ui.home.section

import androidx.recyclerview.widget.GridLayoutManager
import com.jarvis.anime.R
import com.jarvis.anime.base.BaseFragment
import com.jarvis.anime.databinding.FragmentBookSectionBinding
import com.jarvis.anime.model.Work
import com.jarvis.anime.ui.home.section.adapter.BookSectionAdapter
import com.jarvis.anime.viewModel.MainViewModel
import com.jarvis.anime.viewModel.home.BookSectionViewModel

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