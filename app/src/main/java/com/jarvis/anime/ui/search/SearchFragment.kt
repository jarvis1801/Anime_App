package com.jarvis.anime.ui.search

import android.text.Editable
import android.text.TextWatcher
import com.jarvis.anime.R
import com.jarvis.anime.base.BaseFragment
import com.jarvis.anime.databinding.FragmentSearchBinding
import com.jarvis.anime.viewModel.MainViewModel
import com.jarvis.anime.viewModel.search.SearchViewModel

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