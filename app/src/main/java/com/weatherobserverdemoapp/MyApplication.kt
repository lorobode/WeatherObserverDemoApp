package com.weatherobserverdemoapp

import android.app.Activity
import android.app.Application
import com.weatherobserverdemoapp.di.component.DaggerInjection
import com.weatherobserverdemoapp.di.module.DatabaseModule
import com.weatherobserverdemoapp.di.module.NetworkModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber
import javax.inject.Inject

class MyApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        // This will initialise Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // RxJava errorHandler
        RxJavaPlugins.setErrorHandler { Timber.d(it) }

        // Dagger injection
        DaggerInjection.builder()
            .networkModule(NetworkModule)
            .databaseModule(DatabaseModule(this))
            .build().inject(this)
    }

    override fun activityInjector() = activityInjector
}