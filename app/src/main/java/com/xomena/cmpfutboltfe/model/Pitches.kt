package com.xomena.cmpfutboltfe.model

import android.content.Context
import android.widget.Toast
import com.xomena.cmpfutboltfe.R
import com.xomena.cmpfutboltfe.network.PitchesAPI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class Pitches @Inject constructor (private val api: PitchesAPI, private val context: Context) {
    fun getData() {
        Single.fromCallable {
            return@fromCallable api.getData()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ text ->
                // And then we display it.
                Toast.makeText(context, text as CharSequence, Toast.LENGTH_SHORT).show()
            }, {
                Toast.makeText(context,  R.string.noData, Toast.LENGTH_LONG).show()
            })
    }
}