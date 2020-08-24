package com.weatherobserverdemoapp.data.source.remote

import com.weatherobserverdemoapp.data.model.City
import com.weatherobserverdemoapp.data.model.CityCurrentWeather
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface Api {
    @GET("locations/v1/cities/autocomplete")
    fun getCities(
        @Query("apikey") apiKey: String,
        @Query("q") search: String
    ): Observable<List<City>>

    @GET
    fun getCurrentWeather(
        @Url url: String,
        @Query("apikey") apiKey: String
    ): Observable<List<CityCurrentWeather>>
}