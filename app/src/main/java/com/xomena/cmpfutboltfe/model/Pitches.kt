package com.xomena.cmpfutboltfe.model

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.xomena.cmpfutboltfe.PitchesApplication
import com.xomena.cmpfutboltfe.R
import com.xomena.cmpfutboltfe.network.PitchesAPI
import com.xomena.cmpfutboltfe.states.Action.DataLoaded
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.internal.toImmutableList
import org.json.JSONArray
import org.json.JSONException
import javax.inject.Inject


class Pitches @Inject constructor(private val api: PitchesAPI, private val context: Context) {
    private val items: MutableList<Pitch>  = ArrayList<Pitch>()

    fun getData() {
        Single.fromCallable {
            return@fromCallable api.getData()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ text ->
                val json: String = text.substring(0, text.length - 1).substring(2)

                val js_arr: JSONArray
                try {
                    js_arr = JSONArray(json)
                    for (i in 1 until js_arr.length()) {
                        if (!js_arr.isNull(i)) {
                            val js_val = js_arr.getJSONArray(i)
                            items.add(Pitch(js_val))
                        }
                    }
                } catch (ex: JSONException) {
                    Log.e(PitchesApplication.TAG, "JSON Exception", ex)
                }
                getApplication().transitToNextState(DataLoaded(), context)
            }, {
                Toast.makeText(context, R.string.noData, Toast.LENGTH_LONG).show()
            })
    }

    fun getItems(): List<Pitch> {
        return items.toImmutableList()
    }

    private fun getApplication(): PitchesApplication {
        return context as PitchesApplication
    }
}