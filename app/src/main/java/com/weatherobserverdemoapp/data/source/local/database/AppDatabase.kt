package com.weatherobserverdemoapp.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.weatherobserverdemoapp.data.model.SelectedCity
import com.weatherobserverdemoapp.data.model.User
import com.weatherobserverdemoapp.data.source.local.dao.CityDao
import com.weatherobserverdemoapp.data.source.local.dao.UserDao
import com.weatherobserverdemoapp.utils.converter.DateTypeConverter

@Database(
    entities = [User::class, SelectedCity::class],
    version = 1
)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun cityDao(): CityDao
}