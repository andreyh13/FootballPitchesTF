package com.xomena.cmpfutboltfe

import android.app.Application
import android.util.Log
import com.xomena.cmpfutboltfe.dagger.AppComponent
import com.xomena.cmpfutboltfe.dagger.AppModule
import com.xomena.cmpfutboltfe.dagger.DaggerAppComponent

class PitchesApplication() : Application() {
    lateinit var pitchesComponent: AppComponent

    companion object {
        const val TAG = "FootballPitchesTF"
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "Initializing football pitches app");
        pitchesComponent = initDagger(this)
    }

    private fun initDagger(app: PitchesApplication): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
}