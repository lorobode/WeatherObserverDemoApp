package com.weatherobserverdemoapp.data.source.repository

import com.weatherobserverdemoapp.data.model.City
import com.weatherobserverdemoapp.data.model.SelectedCity
import com.weatherobserverdemoapp.data.source.local.dao.CityDao
import com.weatherobserverdemoapp.data.source.remote.Api
import com.weatherobserverdemoapp.utils.WEATHER_API_KEY
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class CityRepository @Inject constructor(private val api: Api, private val cityDao: CityDao) {

    fun getCities(search: String): Observable<List<City>> {
        return api.getCities(WEATHER_API_KEY, search)
    }

    fun addSelectedCity(selectedCity: SelectedCity): Observable<List<Long>> {
        return cityDao.add(selectedCity).toObservable()
    }

    fun cityAdded(cityId: Long): Observable<Int> {
        return cityDao.cityAdded(cityId).toObservable()
    }

    fun selectedCityAvailable(userId: Int): Observable<List<SelectedCity>> {
        return cityDao.getSelectedCitiesAvailable(Date(), userId).toObservable()
    }
}