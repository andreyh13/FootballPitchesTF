package com.xomena.cmpfutboltfe.model

import com.xomena.cmpfutboltfe.network.PitchesAPI
import javax.inject.Inject

class Pitches @Inject constructor (private val api: PitchesAPI) {
    fun getData() {
        api.getData();
    }
}