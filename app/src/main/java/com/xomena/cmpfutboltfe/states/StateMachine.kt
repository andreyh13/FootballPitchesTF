package com.xomena.cmpfutboltfe.states

import android.content.Context
import androidx.compose.runtime.Composable
import javax.inject.Inject

interface PitchesAppState {
    fun consumeAction(action: Action, states: States): PitchesAppState
    fun renderViewState(context: Context)
    @Composable
    fun stateCompose(context: Context)
}

sealed class Action {
    class DataLoaded : Action()
    class DataNotLoaded: Action()
    class CountySelected(val county: String): Action()
}

class States @Inject constructor (
    val splashView: SplashView,
    val countiesView: CountiesView,
    val noDataView: NoDataView,
    val pitchesListView: PitchesListView
) {
}
