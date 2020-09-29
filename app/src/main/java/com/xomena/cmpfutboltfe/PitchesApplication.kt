package com.xomena.cmpfutboltfe

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.xomena.cmpfutboltfe.dagger.AppModule
import com.xomena.cmpfutboltfe.dagger.DaggerAppComponent
import com.xomena.cmpfutboltfe.dagger.NetworkModule
import com.xomena.cmpfutboltfe.model.Pitches
import com.xomena.cmpfutboltfe.states.Action
import com.xomena.cmpfutboltfe.states.PitchesAppState
import com.xomena.cmpfutboltfe.states.States
import java.util.*
import javax.inject.Inject


class PitchesApplication: Application {
    @Inject lateinit var pitches: Pitches
    @Inject lateinit var context: Context
    @Inject lateinit var states: States

    lateinit var currentState: PitchesAppState

    var mActivity: Activity? = null
    var stateHistory: Stack<PitchesAppState> = Stack<PitchesAppState>()

    companion object {
        const val TAG = "FootballPitchesTF"
    }

    constructor(): super() {
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "Initializing football pitches app");
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule(this))
            .build()
            .inject(this)
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
                val callback = object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        transitToPreviousState()
                    }
                }
                (activity as AppCompatActivity).getOnBackPressedDispatcher().addCallback(activity, callback)
            }
            override fun onActivityStarted(activity: Activity) {
                mActivity = activity
            }

            override fun onActivityResumed(activity: Activity) {
                mActivity = activity
                Log.d(TAG, "onActivityResumed:" + activity.localClassName)
            }

            override fun onActivityPaused(activity: Activity) {
                mActivity = null
            }

            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle?) {}
            override fun onActivityDestroyed(activity: Activity) {}
        })
        setState(states.splashView)
        pitches.getData()
    }

    fun getState(): PitchesAppState {
        return currentState
    }

    fun setState(state: PitchesAppState) {
        if (this::currentState.isInitialized) {
            stateHistory.push(currentState)
        }
        currentState = state
    }

    fun transitToNextState(action: Action, context: Context = this) {
        this.setState(
            currentState.consumeAction(
                action,
                this.states
            )
        )
        currentState.renderViewState(context)
    }

    fun transitToPreviousState(context: Context = this) {
        if (!stateHistory.isEmpty()) {
            this.setState(stateHistory.pop())
            currentState.renderViewState(context)
        }
    }

    fun getActivity(): Activity? {
        return mActivity
    }

    fun isPitchesLoaded(): Boolean {
        if (this::pitches.isInitialized) {
            return pitches.getItems().isNotEmpty()
        }
        return false
    }
}