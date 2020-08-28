package com.xomena.cmpfutboltfe.states

import android.content.Context
import android.content.Intent
import com.xomena.cmpfutboltfe.MainActivity
import com.xomena.cmpfutboltfe.R
import javax.inject.Inject

interface PitchesAppState {
    fun consumeAction(action: Action, states: States): PitchesAppState
    fun renderViewState(context: Context)
}

sealed class Action {
    class DataLoaded : Action()
    class DataNotLoaded: Action()
    class CountySelected(val county: String): Action()
}

class SplashView(): PitchesAppState {
    override fun consumeAction(action: Action, states: States): PitchesAppState {
        return when(action) {
            is Action.DataLoaded -> states.countiesView
            is Action.DataNotLoaded -> states.noDataView
            else -> throw IllegalStateException("Invalid action $action passed to state $this")
        }
    }

    override fun renderViewState(context: Context) {
    }
}

class CountiesView(): PitchesAppState {
    override fun consumeAction(action: Action, states: States): PitchesAppState {
        return when(action) {
            is Action.CountySelected -> states.pitchesListView
            else -> throw IllegalStateException("Invalid action $action passed to state $this")
        }
    }

    override fun renderViewState(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent);
    }
}

class NoDataView(): PitchesAppState {
    override fun consumeAction(action: Action, states: States): PitchesAppState {
        return this
    }

    override fun renderViewState(context: Context) {
        context.setTheme(R.style.AppTheme)
    }
}

class PitchesListView(): PitchesAppState {
    override fun consumeAction(action: Action, states: States): PitchesAppState {
        return this
    }

    override fun renderViewState(context: Context) {

    }
}

class States @Inject constructor (
    val splashView: SplashView,
    val countiesView: CountiesView,
    val noDataView: NoDataView,
    val pitchesListView: PitchesListView
) {

}
