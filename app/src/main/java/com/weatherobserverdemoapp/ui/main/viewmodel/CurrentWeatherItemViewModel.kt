package com.weatherobserverdemoapp.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.weatherobserverdemoapp.data.model.CityWeather
import com.weatherobserverdemoapp.ui.base.BaseViewModel
import com.weatherobserverdemoapp.utils.extensions.formatToDateTime
import com.weatherobserverdemoapp.utils.extensions.formatToOnlyDate

class CurrentWeatherItemViewModel : BaseViewModel() {

    val cepText = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val datePeriod = MutableLiveData<String>()
    val weatherText = MutableLiveData<String>()
    val weatherTemp = MutableLiveData<String>()

    val weatherImg = MutableLiveData<Int>()

    fun bind(cityWeather: CityWeather) {
        cepText.value =
            "${cityWeather.selectedCity.city.country?.name} - ${cityWeather.selectedCity.city.name}, ${cityWeather.selectedCity.city.state?.name}"
        date.value = cityWeather.currentWeather.date?.formatToDateTime()
        datePeriod.value =
            "${cityWeather.selectedCity.windowStart.formatToOnlyDate()} ~ ${cityWeather.selectedCity.windowEnd.formatToOnlyDate()}"
        weatherText.value = cityWeather.currentWeather.weatherText
        weatherTemp.value =
            "${cityWeather.currentWeather.temperature.metric.value.toInt()}°${cityWeather.currentWeather.temperature.metric.unit}"
        weatherImg.value = cityWeather.currentWeather.weatherIcon.imgRef
    }
}