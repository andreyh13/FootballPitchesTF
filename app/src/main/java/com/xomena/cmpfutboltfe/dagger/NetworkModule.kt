package com.xomena.cmpfutboltfe.dagger

import com.xomena.cmpfutboltfe.network.PitchesAPI
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object {
        private const val NAME_BASE_URL = "NAME_BASE_URL"
        private const val DATA_SERVICE_URL = "https://script.google.com/macros/s/AKfycbyxqfsV0zdCKFRxgYYWPVO1PMshyhiuvTbvuKkkHjEGimPcdlpd/exec?jsonp=?";
    }

    @Provides
    @Named(NAME_BASE_URL)
    fun provideBaseUrlString() = "${DATA_SERVICE_URL}"

    @Provides
    @Singleton
    fun provideHttpClient() = OkHttpClient()

    @Provides
    @Singleton
    fun provideRequestBuilder(@Named(NAME_BASE_URL) baseUrl: String) =
        baseUrl.toHttpUrlOrNull()?.newBuilder()

    @Provides
    @Singleton
    fun providePitchesApi(client: OkHttpClient, requestBuilder: HttpUrl.Builder?) = PitchesAPI(client, requestBuilder)
}