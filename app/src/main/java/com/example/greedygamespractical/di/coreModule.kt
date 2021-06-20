package com.example.greedygamespractical.di

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val coreModule = module {
    single {
        androidApplication().resources
    }


    //Gson
    single {
        Gson()
    }


    single {
        androidApplication().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }


}