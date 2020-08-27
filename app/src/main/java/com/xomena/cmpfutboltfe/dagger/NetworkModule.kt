package com.xomena.cmpfutboltfe.dagger

import android.content.Context
import com.xomena.cmpfutboltfe.PitchesApplication
import com.xomena.cmpfutboltfe.network.PitchesAPI
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Cache
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule (private val app: PitchesApplication) {
    companion object {
        private const val NAME_BASE_URL = "NAME_BASE_URL"
        private const val DATA_SERVICE_URL = "https://script.google.com/macros/s/AKfycbyxqfsV0zdCKFRxgYYWPVO1PMshyhiuvTbvuKkkHjEGimPcdlpd/exec?jsonp=?";
    }

    @Provides
    @Named(NAME_BASE_URL)
    fun provideBaseUrlString() = "${DATA_SERVICE_URL}"

    @Provides
    @Singleton
    fun provideCacheDir(): File = app.cacheDir

    @Provides
    @Singleton
    fun provideHttpClient() = OkHttpClient.Builder()
        .cache(Cache(
            directory = File(provideCacheDir(), "http_cache"),
            maxSize = 10L * 1024L * 1024L // 10 MiB
        ))
        .build()

    @Provides
    @Singleton
    fun provideRequestBuilder(@Named(NAME_BASE_URL) baseUrl: String) =
        baseUrl.toHttpUrlOrNull()?.newBuilder()

    @Provides
    @Singleton
    fun providePitchesApi(client: OkHttpClient, requestBuilder: HttpUrl.Builder?) = PitchesAPI(client, requestBuilder)
}