package com.smrahmadi.materialnote.dagger.module

import android.app.Application
import dagger.Module
import dagger.Provides


@Module
class AppModule(var application: Application) {

    @Provides
    fun providesApplication() = application

}