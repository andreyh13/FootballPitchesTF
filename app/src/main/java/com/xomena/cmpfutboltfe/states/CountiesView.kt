package com.xomena.cmpfutboltfe.states

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xomena.cmpfutboltfe.MainActivity
import com.xomena.cmpfutboltfe.R

class CountiesView(): PitchesAppState {
    override fun consumeAction(action: Action, states: States): PitchesAppState {
        return when(action) {
            is Action.DataLoaded -> this
            is Action.CountySelected -> states.pitchesListView
            else -> throw IllegalStateException("Invalid action $action passed to state $this")
        }
    }

    override fun renderViewState(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent);
    }

    @Composable
    override fun stateCompose(context: Context) {
        ConstraintLayout {
            val (topBar, text) = createRefs()
            TopAppBar(
                modifier = Modifier.constrainAs(topBar){
                    top.linkTo(parent.top)
                },
                title = { Text(text = context.getString(R.string.app_name)) }
            )
            Text(text = "Hello World!", Modifier.constrainAs(text) {
                top.linkTo(topBar.bottom, margin = 16.dp)
            })
        }
    }
}
