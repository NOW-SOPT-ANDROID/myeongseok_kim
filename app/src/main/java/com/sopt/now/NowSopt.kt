package com.sopt.now

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NowSopt :Application() {
    override fun onCreate() {
        super.onCreate()
    }
}