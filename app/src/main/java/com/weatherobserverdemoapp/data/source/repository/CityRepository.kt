package com.weatherobserverdemoapp.data.source.repository

import com.weatherobserverdemoapp.data.model.City
import com.weatherobserverdemoapp.data.source.remote.Api
import com.weatherobserverdemoapp.utils.WEATHER_API_KEY
import io.reactivex.Observable
import javax.inject.Inject

class CityRepository @Inject constructor(private val api: Api) {

    fun getCities(search: String): Observable<List<City>> {
        return api.getCities(WEATHER_API_KEY, search)
    }
}

