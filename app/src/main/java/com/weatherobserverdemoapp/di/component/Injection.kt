package com.weatherobserverdemoapp.di.component

import com.weatherobserverdemoapp.MyApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(
    modules = [AndroidInjectionModule::class]
)
interface Injection {
    fun inject(app: MyApplication)
}