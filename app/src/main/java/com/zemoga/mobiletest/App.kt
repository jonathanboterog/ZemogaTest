package com.zemoga.mobiletest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object{
        private lateinit var instance: App
    }
}