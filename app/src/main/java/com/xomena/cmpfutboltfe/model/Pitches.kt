package com.xomena.cmpfutboltfe.model

import android.content.Context
import android.widget.Toast
import com.xomena.cmpfutboltfe.PitchesApplication
import com.xomena.cmpfutboltfe.R
import com.xomena.cmpfutboltfe.network.PitchesAPI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import com.xomena.cmpfutboltfe.states.Action.DataLoaded

class Pitches @Inject constructor (private val api: PitchesAPI, private val context: Context) {
    fun getData() {
        Single.fromCallable {
            return@fromCallable api.getData()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ text ->
                getApplication().setState(getApplication().getState().consumeAction(DataLoaded(), getApplication().states))
                getApplication().getState().renderViewState(context)
                // And then we display it.
                Toast.makeText(context, text as CharSequence, Toast.LENGTH_SHORT).show()
            }, {
                Toast.makeText(context,  R.string.noData, Toast.LENGTH_LONG).show()
            })
    }

    private fun getApplication(): PitchesApplication {
        return context as PitchesApplication
    }
}