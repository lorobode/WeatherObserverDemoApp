package com.weatherobserverdemoapp.data.source.repository

import com.weatherobserverdemoapp.data.model.CityCurrentWeather
import com.weatherobserverdemoapp.data.source.remote.Api
import com.weatherobserverdemoapp.utils.BASE_URL
import com.weatherobserverdemoapp.utils.CURRENT_WEATHER_URL
import com.weatherobserverdemoapp.utils.WEATHER_API_KEY
import io.reactivex.Observable
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: Api) {

    fun getCurrentWeather(cityId: Long): Observable<List<CityCurrentWeather>> {
        val url = "$BASE_URL$CURRENT_WEATHER_URL$cityId"
        return api.getCurrentWeather(url, WEATHER_API_KEY)
    }
}