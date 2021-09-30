package com.appmea.connectbutton

import android.app.Application
import android.content.res.Configuration
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        AppConfig.onConfigChanged(this, null)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        AppConfig.onConfigChanged(this, newConfig)
    }
}