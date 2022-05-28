package com.jarvis.anime.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.jarvis.anime.App
import com.jarvis.anime.extension.Extension.Companion.toArrayList
import com.jarvis.anime.model.media.Image
import com.jarvis.anime.model.media.ImageUpdateImageString
import com.jarvis.anime.repository.Status
import com.jarvis.anime.repository.resource.ResourceRemoteDataSource
import com.jarvis.anime.util.FileUtil
import com.jarvis.anime.util.FileUtil.PATH_APP_SPECIFIC_FOLDER
import com.jarvis.anime.util.FileUtil.getAndroidFolderStructure
import com.jarvis.anime.util.FileUtil.isFileExist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MangaChapterDownloadService: Service() {

    companion object {
        const val KEY_IMAGE_ID_LIST = "KEY_IMAGE_ID_LIST"
    }

    private val job = SupervisorJob()
    private val scope = CoroutineScope(IO + job)

//    private val iBinder = IBinder()

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        scope.launch(IO) {
            val imageIdList = intent?.getStringArrayListExtra(KEY_IMAGE_ID_LIST).toArrayList()
            val imageList = App.database.imageDao().getByImageListByIdList(imageIdList)?.filter {
                it.imageString.isNullOrEmpty()
            }
            imageList?.forEach { image ->
                withContext(IO) { downloadImage(image) }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private suspend fun downloadImage(image: Image) {
        Log.d("chris", "downloadImage ${image.url}")
        image.url?.let { url ->
            val filePullPath = "$PATH_APP_SPECIFIC_FOLDER${url.getAndroidFolderStructure()}"
            if (!filePullPath.isFileExist()) {
                val resource = withContext(IO) { ResourceRemoteDataSource().getImageResource(url) }
                val data = resource.data
                val status = resource.status
                if (status == Status.SUCCESS && !data.isNullOrEmpty() && data != "Error") {
                    FileUtil.createImageFile(filePullPath, data)
//                image.imageString = data
//                updateImage(image)
                }
            }
        }
    }

    private fun updateImage(image: Image) {
        App.database.imageDao().updateImageString(ImageUpdateImageString(image))
    }

    private suspend fun deleteImage(image: Image) = withContext(IO) {
        image.imageString = null
        updateImage(image)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

//    inner class IBinder : Binder() {
//        val service: Service
//            get() = this@MangaChapterDownloadService
//    }
}