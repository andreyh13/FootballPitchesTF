package com.xomena.cmpfutboltfe.dagger

import com.xomena.cmpfutboltfe.PitchesApplication
import javax.inject.Singleton
import dagger.Component

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(app: PitchesApplication)
}