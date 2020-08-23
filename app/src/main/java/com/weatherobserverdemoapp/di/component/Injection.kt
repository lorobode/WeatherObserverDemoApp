package com.weatherobserverdemoapp.di.component

import com.weatherobserverdemoapp.MyApplication
import com.weatherobserverdemoapp.di.module.ActivityModule
import com.weatherobserverdemoapp.di.module.DatabaseModule
import com.weatherobserverdemoapp.di.module.FactoryModule
import com.weatherobserverdemoapp.di.module.NetworkModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(
    modules = [AndroidInjectionModule::class, ActivityModule::class,
        FactoryModule::class, DatabaseModule::class, NetworkModule::class]
)
interface Injection {
    fun inject(app: MyApplication)
}