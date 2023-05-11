package com.easyapply.myapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import kotlin.text.Typography.dagger


@HiltAndroidApp
class MyApp : Application() {
    companion object {
        lateinit var baseApplication: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        baseApplication = this

        Timber.plant(Timber.DebugTree())
    }
}