package com.weatherobserverdemoapp.di.module

import androidx.lifecycle.ViewModelProvider
import com.weatherobserverdemoapp.ui.main.viewmodel.CityViewModelFactory
import com.weatherobserverdemoapp.ui.main.viewmodel.MainViewModelFactory
import com.weatherobserverdemoapp.ui.main.viewmodel.UserViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class FactoryModule {

    @Provides
    fun provideMainViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory =
        factory

    @Provides
    fun provideUserViewModelFactory(factory: UserViewModelFactory): ViewModelProvider.Factory =
        factory

    @Provides
    fun provideCityViewModelFactory(factory: CityViewModelFactory): ViewModelProvider.Factory =
        factory
}