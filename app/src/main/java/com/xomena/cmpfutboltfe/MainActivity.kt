package com.xomena.cmpfutboltfe

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.xomena.cmpfutboltfe.model.PitchesViewModel
import com.xomena.cmpfutboltfe.ui.ComposeApplicationTheme

class MainActivity : AppCompatActivity() {
    val pitchesViewModel by viewModels<PitchesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    (application as PitchesApplication).getState().stateCompose(this , pitchesViewModel)
                }
            }
        }
    }
}