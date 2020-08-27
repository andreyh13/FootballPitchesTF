package com.xomena.cmpfutboltfe.dagger

import com.xomena.cmpfutboltfe.PitchesApplication
import android.content.Context
import com.xomena.cmpfutboltfe.model.Pitches
import com.xomena.cmpfutboltfe.network.PitchesAPI
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: PitchesApplication) {
    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun providePitches(api: PitchesAPI) = Pitches(api, provideContext())
}
