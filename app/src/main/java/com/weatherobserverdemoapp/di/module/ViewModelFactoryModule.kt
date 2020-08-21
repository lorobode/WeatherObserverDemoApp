package com.weatherobserverdemoapp.di.module

import androidx.lifecycle.ViewModelProvider
import com.weatherobserverdemoapp.ui.main.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelFactoryModule {

    @Provides
    fun provideMainViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory =
        factory
}