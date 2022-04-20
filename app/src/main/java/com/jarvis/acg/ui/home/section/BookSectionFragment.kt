package com.jarvis.acg.ui.home.section

import androidx.recyclerview.widget.GridLayoutManager
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseFragment
import com.jarvis.acg.databinding.FragmentBookSectionBinding
import com.jarvis.acg.model.Work
import com.jarvis.acg.ui.home.section.adapter.BookSectionAdapter
import com.jarvis.acg.viewModel.EmptyViewModel
import com.jarvis.acg.viewModel.MainViewModel

abstract class BookSectionFragment : BaseFragment<FragmentBookSectionBinding, EmptyViewModel, MainViewModel>() {
    private lateinit var sectionAdapter: BookSectionAdapter

    override fun getLayoutId(): Int { return R.layout.fragment_book_section }

    override fun getViewModelClass(): Class<EmptyViewModel> { return EmptyViewModel::class.java }

    override fun getActivityViewModelClass(): Class<MainViewModel> { return MainViewModel::class.java }

    override fun initView() {
        sectionAdapter = BookSectionAdapter(requireContext())

        getDataBinding().rvWork.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = sectionAdapter
        }
    }

    override fun initListener() {
    }

    override fun initStartEvent() {
        observeViewModel()
    }

    private fun observeViewModel() {
        if (this is NovelSectionFragment) {
            mActivityViewModel?.novelWorkList?.observe(requireActivity()) { workList ->
                updateWorkList(workList)
            }
        } else if (this is MangaSectionFragment) {
            mActivityViewModel?.mangaWorkList?.observe(requireActivity()) { workList ->
                updateWorkList(workList)
            }
        }
    }

    private fun updateWorkList(workList: ArrayList<Work>) {
        val resultList = arrayListOf<Any>().apply { addAll(workList) }
        sectionAdapter.clearData()
        sectionAdapter.addAllData(resultList)
    }
}