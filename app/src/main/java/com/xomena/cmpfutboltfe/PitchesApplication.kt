package com.xomena.cmpfutboltfe

import android.app.Application
import android.util.Log
import com.xomena.cmpfutboltfe.dagger.AppComponent
import com.xomena.cmpfutboltfe.dagger.AppModule
import com.xomena.cmpfutboltfe.dagger.DaggerAppComponent
import com.xomena.cmpfutboltfe.dagger.NetworkModule
import com.xomena.cmpfutboltfe.model.Pitches
import javax.inject.Inject

class PitchesApplication: Application {
    @Inject lateinit var pitches: Pitches

    companion object {
        const val TAG = "FootballPitchesTF"
    }

    constructor(): super() {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .build()
            .inject(this)
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "Initializing football pitches app");
        pitches.getData()
    }
}