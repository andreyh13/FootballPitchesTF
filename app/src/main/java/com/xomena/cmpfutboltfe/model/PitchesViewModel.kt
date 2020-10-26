package com.xomena.cmpfutboltfe.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xomena.cmpfutboltfe.PitchesApplication
import com.xomena.cmpfutboltfe.ui.composable.CountiesViewsTabs

class PitchesViewModel constructor (private val app: Application): AndroidViewModel(app) {
    private val _countiesViewSelectedTab = MutableLiveData(CountiesViewsTabs.Counties)
    val countiesViewSelectedTab: LiveData<CountiesViewsTabs> = _countiesViewSelectedTab

    fun getPitches(): List<Pitch> {
        if (getApp().isPitchesLoaded()) {
            return getApp().pitches.getItems();
        }
        return ArrayList()
    }

    fun hasPitches(): Boolean {
        return getApp().isPitchesLoaded()
    }

    fun onCountiesViewSelectedTabChanged(newTab: CountiesViewsTabs) {
        _countiesViewSelectedTab.value = newTab
    }

    private fun getApp(): PitchesApplication {
        return (app as PitchesApplication)
    }
}