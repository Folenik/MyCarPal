package com.mosz.wikirandom

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        println("onCreate")
        if (BuildConfig.DEBUG && add()) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun add(): Boolean {
        return true
    }
}