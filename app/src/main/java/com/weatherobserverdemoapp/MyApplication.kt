package com.weatherobserverdemoapp

import android.app.Activity
import android.app.Application
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModelProvider
import com.weatherobserverdemoapp.di.component.DaggerInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyApplication : Application(), HasActivityInjector, HasDefaultViewModelProviderFactory {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var viewModelFactoryInjector: ViewModelProvider.Factory

    override fun onCreate() {
        super.onCreate()

        // Dagger injection
        DaggerInjection.builder()
            .build().inject(this)
    }

    override fun activityInjector() = activityInjector

    override fun getDefaultViewModelProviderFactory() = viewModelFactoryInjector
}