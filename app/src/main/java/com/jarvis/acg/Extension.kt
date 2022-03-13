package com.jarvis.acg

import android.content.Context
import android.content.Intent
import android.os.Bundle

class Extension {
    companion object {
        inline fun <reified T : Any> Context.launchActivity(options: Bundle? = null, noinline init: Intent.() -> Unit = {}) {
            val intent = Intent(this, T::class.java)
            intent.init()
            startActivity(intent, options)
        }
    }
}