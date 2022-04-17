package com.jarvis.acg.ui.home.section

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseFragment
import com.jarvis.acg.databinding.FragmentNovelSectionBinding
import com.jarvis.acg.model.Work
import com.jarvis.acg.ui.home.section.adapter.NovelAdapter
import com.jarvis.acg.viewModel.EmptyViewModel
import com.jarvis.acg.viewModel.MainViewModel

class NovelSectionFragment : BaseFragment<FragmentNovelSectionBinding, EmptyViewModel, MainViewModel>() {

    private lateinit var novelAdapter: NovelAdapter

    override fun getLayoutId(): Int { return R.layout.fragment_novel_section }

    override fun getViewModelClass(): Class<EmptyViewModel> { return EmptyViewModel::class.java }

    override fun getActivityViewModelClass(): Class<MainViewModel> { return MainViewModel::class.java }

    override fun initView() {
        novelAdapter = NovelAdapter(requireContext())

        getDataBinding().rvNovel.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = novelAdapter
        }
    }

    override fun initListener() {
    }

    override fun initStartEvent() {
        observeViewModel()
    }

    private fun observeViewModel() {
        mActivityViewModel?.novelWorkList?.observe(requireActivity()) { workList ->
            Log.d("chris", "workList ${workList.size}")
            updateNovelWorkList(workList)
        }
    }

    private fun updateNovelWorkList(workList: ArrayList<Work>) {
        val resultList = arrayListOf<Any>().apply { addAll(workList) }
        novelAdapter.clearData()
        novelAdapter.addAllData(resultList)
    }
}