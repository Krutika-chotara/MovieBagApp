package com.example.greedygamespractical

import android.app.Application
import com.example.greedygamespractical.di.coreModule
import com.example.greedygamespractical.di.networkModule
import com.example.greedygamespractical.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(networkModule, vmModule, coreModule))
        }
    }
}