package com.weatherobserverdemoapp.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class CityViewModelFactory @Inject constructor(
    private val cityViewModel: CityViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityViewModel::class.java)) {
            return cityViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}