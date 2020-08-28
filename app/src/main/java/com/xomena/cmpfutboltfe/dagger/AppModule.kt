package com.xomena.cmpfutboltfe.dagger

import com.xomena.cmpfutboltfe.PitchesApplication
import android.content.Context
import com.xomena.cmpfutboltfe.model.Pitches
import com.xomena.cmpfutboltfe.network.PitchesAPI
import com.xomena.cmpfutboltfe.states.*
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

    @Provides
    @Singleton
    fun provideSplashView(): SplashView = SplashView()

    @Provides
    @Singleton
    fun provideCountiesView(): CountiesView = CountiesView()

    @Provides
    @Singleton
    fun provideNoDataView(): NoDataView = NoDataView()

    @Provides
    @Singleton
    fun providePitchesListView(): PitchesListView = PitchesListView()

    @Provides
    @Singleton
    fun provideStates(): States = States(
        provideSplashView(),
        provideCountiesView(),
        provideNoDataView(),
        providePitchesListView()
    )
}
