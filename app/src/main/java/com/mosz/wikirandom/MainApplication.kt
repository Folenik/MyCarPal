package com.mosz.wikirandom

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {

    //TODO matke
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())


        println("mlogi")

    }
}