package com.xomena.cmpfutboltfe.states

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.xomena.cmpfutboltfe.MainActivity
import com.xomena.cmpfutboltfe.R
import com.xomena.cmpfutboltfe.model.PitchesViewModel
import com.xomena.cmpfutboltfe.ui.composable.CountiesViewsTabsCompose

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
    override fun stateCompose(context: Context, model: PitchesViewModel) {
        ConstraintLayout {
            val (topBar, content) = createRefs()
            TopAppBar(
                modifier = Modifier.constrainAs(topBar){
                    top.linkTo(parent.top)
                },
                title = { Text(text = context.getString(R.string.app_name)) }
            )
            Column(Modifier.fillMaxWidth().constrainAs(content) {
                top.linkTo(topBar.bottom)
            }) {
                CountiesViewsTabsCompose(model)
            }
        }
    }
}
