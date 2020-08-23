package com.weatherobserverdemoapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.weatherobserverdemoapp.data.model.User
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getAll(): Single<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(vararg user: User): Single<List<Long>>

    @Query("SELECT * from User where id = :id LIMIT 1")
    fun loadById(id: Int): Flowable<User>
}