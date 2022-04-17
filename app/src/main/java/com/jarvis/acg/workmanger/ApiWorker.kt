package com.jarvis.acg.workmanger

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.jarvis.acg.extension.Extension.Companion.mapToRequestBody

@Suppress("UNCHECKED_CAST")
class ApiWorker(appContext: Context, workerParams: WorkerParameters): CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        val apiUrl = inputData.getString(KEY_API_URL)
        val requestMethod = inputData.getString(KEY_REQUEST_METHOD)
        val responseBody = (inputData.keyValueMap.apply {
            remove(KEY_API_URL)
            remove(KEY_REQUEST_METHOD)
        }.filter { it.value is String } as Map<String, String>).mapToRequestBody()

        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }

    companion object {
        const val KEY_API_URL = "KEY_URL"
        const val KEY_REQUEST_METHOD = "KEY_REQUEST_METHOD"
    }
}