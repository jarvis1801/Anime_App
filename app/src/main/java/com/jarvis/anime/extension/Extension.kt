package com.jarvis.anime.extension

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.FormBody
import okhttp3.RequestBody

class Extension {
    companion object {
        
        inline fun <reified T : Any> Context.launchActivity(options: Bundle? = null, noinline init: Intent.() -> Unit = {}) {
            val intent = Intent(this, T::class.java)
            intent.init()
            startActivity(intent, options)
        }

        inline fun <reified T> Gson.fromJson(json: String): T = fromJson(json, object : TypeToken<T>() {}.type)

        fun Map<String, String>.mapToRequestBody(): RequestBody {
            val formBodyBuilder = FormBody.Builder()
            this.forEach { item -> formBodyBuilder.add(item.key, item.value) }
            return formBodyBuilder.build()
        }

        fun ArrayList<String>.join(separator: String? = ",") : String {
            return this.joinToString(separator = separator.toString())
        }

        inline fun <reified T> List<T>?.toArrayList() : ArrayList<T> {
            return this?.toCollection(ArrayList()) ?: arrayListOf()
        }

        val Number.toPx get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics)
    }
}