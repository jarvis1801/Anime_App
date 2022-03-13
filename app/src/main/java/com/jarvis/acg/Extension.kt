package com.jarvis.acg

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Extension {
    companion object {
        
        inline fun <reified T : Any> Context.launchActivity(options: Bundle? = null, noinline init: Intent.() -> Unit = {}) {
            val intent = Intent(this, T::class.java)
            intent.init()
            startActivity(intent, options)
        }

        inline fun <reified T> Gson.fromJson(json: String): T = fromJson(json, object : TypeToken<T>() {}.type)
    }
}