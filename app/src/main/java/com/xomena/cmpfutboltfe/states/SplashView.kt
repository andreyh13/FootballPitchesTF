package com.xomena.cmpfutboltfe.states

import android.content.Context
import androidx.compose.runtime.Composable
import com.xomena.cmpfutboltfe.model.PitchesViewModel

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

    @Composable
    override fun stateCompose(context: Context, model: PitchesViewModel) {}
}
