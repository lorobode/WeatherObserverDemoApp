package com.weatherobserverdemoapp.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.weatherobserverdemoapp.ui.base.BaseViewModel

class UserItemViewModel : BaseViewModel() {

    val name = MutableLiveData<String>()

    fun bind(text: String) {
        name.value = text
    }
}