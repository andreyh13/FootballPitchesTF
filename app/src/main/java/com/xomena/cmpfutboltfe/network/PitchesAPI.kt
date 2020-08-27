package com.xomena.cmpfutboltfe.network

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject
import java.io.IOException

class PitchesAPI @Inject constructor(
    private val client: OkHttpClient,
    private val requestBuilder: HttpUrl.Builder?
) {
    fun getData() {
        val url: String = requestBuilder?.build().toString()
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            //TODO
            //println(response.body!!.string())
        }
    }
}