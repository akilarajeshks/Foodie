package com.zestworks.foodie

import android.app.Application
import com.zestworks.foodie.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FoodieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FoodieApplication)
            modules(applicationModule)
        }
    }
}