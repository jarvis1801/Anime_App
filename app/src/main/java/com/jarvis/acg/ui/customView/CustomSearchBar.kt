package com.jarvis.acg.ui.customView

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.jarvis.acg.databinding.PanelSearchBarBinding

class CustomSearchBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: PanelSearchBarBinding

    private var watcher: TextWatcher? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = PanelSearchBarBinding.inflate(inflater, this, true)

        initListener()
    }

    private fun initListener() {
        binding.tietSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                watcher?.beforeTextChanged(s, start, count, after)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                watcher?.onTextChanged(s, start, before, count)
            }

            override fun afterTextChanged(s: Editable?) {
                watcher?.afterTextChanged(s)
            }

        })
    }

    fun setOnTextChangeListener(watcher: TextWatcher) {
        this.watcher = watcher
    }
}