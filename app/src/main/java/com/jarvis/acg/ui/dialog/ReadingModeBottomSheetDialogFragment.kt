package com.jarvis.acg.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jarvis.acg.databinding.FragmentReadingModeBottomSheetDialogBinding
import com.jarvis.acg.util.ReadingModeUtil

class ReadingModeBottomSheetDialogFragment(val onClickItem: () -> Unit): BottomSheetDialogFragment() {

    private lateinit var readingModeAdapter: ReadingModeAdapter

    private lateinit var binding: FragmentReadingModeBottomSheetDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        binding = FragmentReadingModeBottomSheetDialogBinding.inflate(LayoutInflater.from(context))
        val view = binding.root
        dialog.setContentView(view)
        initView(dialog)
        return dialog
    }

    private fun initView(dialog: Dialog) {
        readingModeAdapter = ReadingModeAdapter(requireContext()) {
            ReadingModeUtil().setReadingMode(it)
            onClickItem()
            dismiss()
        }.apply {
            addAllData(ReadingModeUtil.READING_MODE_LIST, false)
        }

        binding.recyclerviewReadingModeList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = readingModeAdapter
        }

        dialog.setOnShowListener { dialogInterface ->
            val d = dialogInterface as BottomSheetDialog

            val bottomSheet = d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val mBottomSheetBehavior: BottomSheetBehavior<View> = BottomSheetBehavior.from(bottomSheet)
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
        }
    }
}