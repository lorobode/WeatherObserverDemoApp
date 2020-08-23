package com.weatherobserverdemoapp.di.module

import android.app.Application
import androidx.room.Room
import com.weatherobserverdemoapp.data.source.local.dao.UserDao
import com.weatherobserverdemoapp.data.source.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, "weather_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()
}