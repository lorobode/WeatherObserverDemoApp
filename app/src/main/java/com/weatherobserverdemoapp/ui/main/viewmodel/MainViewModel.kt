package com.weatherobserverdemoapp.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.weatherobserverdemoapp.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {

    val helloWorld = MutableLiveData<String>()

    init {
        helloWorld.value = "Hello World!"
    }
}