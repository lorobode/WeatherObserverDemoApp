package com.weatherobserverdemoapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.weatherobserverdemoapp.data.model.SelectedCity
import io.reactivex.Flowable
import io.reactivex.Single
import java.util.*

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(vararg selectedCity: SelectedCity): Single<List<Long>>

    @Query("SELECT COUNT(*) from SelectedCity where city_id = :id LIMIT 1")
    fun cityAdded(id: Long): Single<Int>

    @Query("SELECT * from SelectedCity where userId = :userId AND windowStart < :date AND windowEnd > :date")
    fun getSelectedCitiesAvailable(date: Date, userId: Int): Flowable<List<SelectedCity>>
}