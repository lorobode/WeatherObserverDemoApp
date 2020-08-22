package com.weatherobserverdemoapp

import android.app.Activity
import android.app.Application
import com.weatherobserverdemoapp.di.component.DaggerInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        // Dagger injection
        DaggerInjection.builder()
            .build().inject(this)
    }

    override fun activityInjector() = activityInjector
}