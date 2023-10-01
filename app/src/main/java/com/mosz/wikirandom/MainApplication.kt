package com.mosz.wikirandom

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {
//TODO asd
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())


        print("asd")
    }
}