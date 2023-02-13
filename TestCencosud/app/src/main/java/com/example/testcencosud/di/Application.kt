package com.example.testcencosud.di

import android.app.Application
import android.os.SystemClock
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        SystemClock.sleep(2000)
    }
}