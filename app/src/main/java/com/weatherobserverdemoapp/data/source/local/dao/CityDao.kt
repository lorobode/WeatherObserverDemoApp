package com.weatherobserverdemoapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.weatherobserverdemoapp.data.model.SelectedCity
import com.weatherobserverdemoapp.data.model.User
import io.reactivex.Single

@Dao
interface CityDao {

    @Query("SELECT * FROM User")
    fun getAll(): Single<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(vararg selectedCity: SelectedCity): Single<List<Long>>

    @Query("SELECT COUNT(*) from SelectedCity where city_id = :id LIMIT 1")
    fun cityAdded(id: Long): Single<Int>
}