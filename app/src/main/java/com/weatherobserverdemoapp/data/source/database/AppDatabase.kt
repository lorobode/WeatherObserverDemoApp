package com.weatherobserverdemoapp.data.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.weatherobserverdemoapp.data.model.User
import com.weatherobserverdemoapp.data.source.dao.UserDao

@Database(
    entities = [User::class],
    version = 1
)
@TypeConverters()
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}