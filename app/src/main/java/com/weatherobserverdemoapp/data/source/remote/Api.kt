package com.weatherobserverdemoapp.data.source.remote

import com.weatherobserverdemoapp.data.model.City
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("locations/v1/cities/autocomplete")
    fun getCities(
        @Query("apikey") apiKey: String,
        @Query("q") search: String
    ): Observable<List<City>>
}