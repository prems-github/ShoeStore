package com.udacity.shoestore

import android.app.Application
import timber.log.Timber

//created application class to implement Timber logging sservice

class ShoeStoreApplication(): Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}