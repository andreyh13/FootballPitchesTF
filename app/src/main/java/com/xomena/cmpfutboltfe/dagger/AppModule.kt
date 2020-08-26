package com.xomena.cmpfutboltfe.dagger

import com.xomena.cmpfutboltfe.PitchesApplication
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: PitchesApplication) {
    @Provides
    @Singleton
    fun provideContext(): Context = app
}
