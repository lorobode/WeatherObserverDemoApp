package com.weatherobserverdemoapp.di.module

import com.weatherobserverdemoapp.ui.main.activity.CityActivity
import com.weatherobserverdemoapp.ui.main.activity.MainActivity
import com.weatherobserverdemoapp.ui.main.activity.UserActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeUserActivity(): UserActivity

    @ContributesAndroidInjector
    abstract fun contributeCityActivity(): CityActivity
}