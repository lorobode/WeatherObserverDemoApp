package com.weatherobserverdemoapp.ui.main.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.weatherobserverdemoapp.R
import com.weatherobserverdemoapp.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {

    val listMessage = MutableLiveData<Int>()
    val userImage = MutableLiveData<Int>()
    val userName = MutableLiveData<Int>()
    val userNameVisibility = MutableLiveData<Int>()

    val userLoaded = false

    init {
        if (userLoaded) {

        } else {
            listMessage.value = R.string.select_user
            userImage.value = R.drawable.baseline_add_circle_24
            userNameVisibility.value = View.GONE
        }
    }
}