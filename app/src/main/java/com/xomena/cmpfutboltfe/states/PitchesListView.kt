package com.xomena.cmpfutboltfe.states

import android.content.Context
import androidx.compose.runtime.Composable
import com.xomena.cmpfutboltfe.model.PitchesViewModel

class PitchesListView(): PitchesAppState {
    override fun consumeAction(action: Action, states: States): PitchesAppState {
        return this
    }

    override fun renderViewState(context: Context) {

    }

    @Composable
    override fun stateCompose(context: Context, model: PitchesViewModel) {}
}
