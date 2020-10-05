package com.xomena.cmpfutboltfe.states

import android.content.Context
import androidx.compose.runtime.Composable

class PitchesListView(): PitchesAppState {
    override fun consumeAction(action: Action, states: States): PitchesAppState {
        return this
    }

    override fun renderViewState(context: Context) {

    }

    @Composable
    override fun stateCompose(context: Context) {}
}
