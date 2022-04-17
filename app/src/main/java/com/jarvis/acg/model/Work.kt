package com.jarvis.acg.model

import android.view.View
import androidx.navigation.findNavController
import androidx.room.Entity
import androidx.room.Ignore

@Entity(tableName = "work")
data class Work(
    var thumbnail: ArrayList<String?>? = null,
    var tag_id_list: ArrayList<String>? = null,

    // extra
    @Ignore var novel_id: String? = null
) : BaseNameObject() {

    fun goToNovelPage(view: View) {
        view.context?.let {

        }
    }
}