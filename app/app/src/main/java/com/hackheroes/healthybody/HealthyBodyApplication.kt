package com.hackheroes.healthybody

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HealthyBodyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}