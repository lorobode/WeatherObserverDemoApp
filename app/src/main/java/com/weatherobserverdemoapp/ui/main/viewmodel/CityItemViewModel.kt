package com.weatherobserverdemoapp.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.weatherobserverdemoapp.data.model.City
import com.weatherobserverdemoapp.ui.base.BaseViewModel

class CityItemViewModel : BaseViewModel() {

    val cityName = MutableLiveData<String>()
    val stateName = MutableLiveData<String>()
    val countryName = MutableLiveData<String>()

    fun bind(city: City) {
        cityName.value = city.name
        stateName.value = city.state?.name
        countryName.value = city.country?.name
    }
}