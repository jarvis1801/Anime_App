package com.jarvis.acg

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.jarvis.acg.repository.localDataSource.AppDatabase
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class App : Application() {

    companion object {
        lateinit var instance: App
        @JvmStatic lateinit var database: AppDatabase
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this, AppDatabase::class.java, applicationContext.packageName)
//            .addMigrations(MIGRATION_1_2)
            .build()
    }

    fun getStringFromAsset(fileName: String) : String? {
        try {
            val inputStream = applicationContext.assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            return String(buffer)
        } catch (e: IOException) { e.printStackTrace() }

        return null
    }
}