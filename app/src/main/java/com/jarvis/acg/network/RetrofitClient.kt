package com.jarvis.acg.network

import com.jarvis.acg.App
import com.jarvis.acg.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object {
        val BASE_URL = App.instance.resources.getString(R.string.domain)
        val GITHUB_URL = App.instance.resources.getString(R.string.github_domain)
        const val TIME_OUT = 3L
    }

    inline fun <reified T> getService(): T = getRetrofitClient().create(T::class.java)

    fun getRetrofitClient() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
    }

    fun getGitHubRetrofitClient() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(GITHUB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
    }

    private fun getOkHttpClient() : OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(getLoggingInterceptor())
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS)
        builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS)

        return builder.build()
    }

    private fun getLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }
}