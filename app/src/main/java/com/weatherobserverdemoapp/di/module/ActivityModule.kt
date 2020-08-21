package com.weatherobserverdemoapp.di.module

import com.weatherobserverdemoapp.ui.main.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}