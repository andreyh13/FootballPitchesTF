package com.xomena.cmpfutboltfe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xomena.cmpfutboltfe.states.Action

class SplashThemeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_theme)
    }

    override fun onStart() {
        super.onStart()
        checkLoadedState()
    }

    override fun onRestart() {
        super.onRestart()
        checkLoadedState()
    }

    override fun onResume() {
        super.onResume()
        checkLoadedState()
    }

    private fun getApp(): PitchesApplication {
        return application as PitchesApplication
    }

    private fun checkLoadedState() {
        if (getApp().isPitchesLoaded()) {
            getApp().transitToNextState(Action.DataLoaded(), getApp())
        }
    }
}