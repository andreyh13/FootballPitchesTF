package com.xomena.cmpfutboltfe

import android.app.Application
import android.content.Context
import android.util.Log
import com.xomena.cmpfutboltfe.dagger.AppComponent
import com.xomena.cmpfutboltfe.dagger.AppModule
import com.xomena.cmpfutboltfe.dagger.DaggerAppComponent
import com.xomena.cmpfutboltfe.dagger.NetworkModule
import com.xomena.cmpfutboltfe.model.Pitches
import javax.inject.Inject

class PitchesApplication: Application {
    @Inject lateinit var pitches: Pitches
    @Inject lateinit var context: Context

    companion object {
        const val TAG = "FootballPitchesTF"
    }

    constructor(): super() {
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "Initializing football pitches app");
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule(this))
            .build()
            .inject(this)
        pitches.getData()
    }
}