package com.jarvis.anime.util

import android.util.Log
import com.jarvis.anime.App
import com.jarvis.anime.extension.Extension.Companion.join
import com.jarvis.anime.extension.Extension.Companion.toArrayList
import java.io.*

object FileUtil {

    var PATH_APP_SPECIFIC_FOLDER = App.instance.applicationContext.getExternalFilesDir(null)?.absolutePath

    fun String.isFileExist(): Boolean {
        val file = File(this.replace("\\", "/"))
        return file.exists()
    }

    fun createImageFile(url: String, imageString: String) {
        val fileName = url.split("/").let { it[it.size - 1]}
        val path = url.split("/").toArrayList().apply { remove(fileName) }.join("/")
        val filePath = File(path)
        Log.d("chris", "$path : $fileName")
        if (!filePath.exists())
            filePath.mkdirs()

        val file = File("$path/$fileName")
        if (!file.exists()) {
            file.createNewFile()

            val buf = BufferedWriter(FileWriter(file, true))
            buf.append(imageString)

            buf.close()
        }
    }

    fun String.readFileString() : String {
        val text = StringBuilder()

        try {
            val br = BufferedReader(FileReader(this))
            var line: String?
            while (br.readLine().also { line = it } != null) {
                text.append(line)
            }
            br.close()
        } catch (e: IOException) {
            //You'll need to add proper error handling here
        }

        return text.toString()
    }

    fun String.getAndroidFolderStructure() : String {
        return this.replace("\\", "/")
    }
}