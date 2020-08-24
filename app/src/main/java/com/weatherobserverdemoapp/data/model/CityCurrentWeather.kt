package com.weatherobserverdemoapp.data.model

import com.squareup.moshi.Json
import com.weatherobserverdemoapp.utils.enums.WeatherIcon
import java.io.Serializable
import java.util.*

data class CityWeather(
    val city: City,
    val currentWeather: CityCurrentWeather
)

data class CityCurrentWeather(
    @Json(name = "LocalObservationDateTime")
    var date: Date?,
    @Json(name = "WeatherText")
    var weatherText: String?,
    @Json(name = "WeatherIcon")
    var weatherIcon: WeatherIcon,
    @Json(name = "Temperature")
    var temperature: Temperature

) : Serializable

data class Temperature(
    @Json(name = "Metric")
    var metric: Metric
)

data class Metric(
    @Json(name = "Value")
    var value: Double,
    @Json(name = "Unit")
    var unit: String
)
