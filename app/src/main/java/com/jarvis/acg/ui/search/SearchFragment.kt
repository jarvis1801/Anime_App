package com.jarvis.acg.ui.search

import android.text.Editable
import android.text.TextWatcher
import com.jarvis.acg.R
import com.jarvis.acg.base.BaseFragment
import com.jarvis.acg.databinding.FragmentSearchBinding
import com.jarvis.acg.viewModel.MainViewModel
import com.jarvis.acg.viewModel.search.SearchViewModel

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel, MainViewModel>() {
    override fun getLayoutId(): Int = R.layout.fragment_search

    override fun getViewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java

    override fun getActivityViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun initView() {}

    override fun initListener() {
        getDataBinding().searchPanel.setOnTextChangeListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    override fun initStartEvent() {}
}