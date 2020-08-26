package com.xomena.cmpfutboltfe.dagger

import javax.inject.Singleton
import dagger.Component

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
}