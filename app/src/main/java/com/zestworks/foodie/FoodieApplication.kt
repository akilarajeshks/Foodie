package com.zestworks.foodie

import android.app.Application
import com.zestworks.foodie.di.applicationModule
import org.koin.core.context.startKoin
import org.koin.dsl.module

class FoodieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            module { applicationModule }
        }
    }
}