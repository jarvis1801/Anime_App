package com.jarvis.anime.ui.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GeneralDialog : DialogFragment() {

    companion object {
        const val TAG_UPDATE_DIALOG = "UPDATE_DIALOG"
    }

    var title: String? = null
    var message: String? = null
    var neutralButtonText: String? = null
    var neutralButtonCallback: DialogInterface.OnClickListener? = null
    var negativeButtonText: String? = null
    var negativeButtonCallback: DialogInterface.OnClickListener? = null
    var positiveButtonText: String? = null
    var positiveButtonCallback: DialogInterface.OnClickListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return requireActivity().let {
            val builder = MaterialAlertDialogBuilder(requireContext())
            builder.apply {
                title?.apply { setTitle(title) }
                message?.apply { setMessage(message) }
                neutralButtonText?.apply { setNeutralButton(neutralButtonText, neutralButtonCallback) }
                negativeButtonText?.apply { setNegativeButton(negativeButtonText, negativeButtonCallback) }
                positiveButtonText?.apply { setPositiveButton(positiveButtonText, positiveButtonCallback) }
            }
            builder.create()
        }
    }
}