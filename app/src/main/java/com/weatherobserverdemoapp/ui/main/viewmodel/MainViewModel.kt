package com.weatherobserverdemoapp.ui.main.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.weatherobserverdemoapp.R
import com.weatherobserverdemoapp.data.model.CityWeather
import com.weatherobserverdemoapp.data.model.SelectedCity
import com.weatherobserverdemoapp.data.source.repository.CityRepository
import com.weatherobserverdemoapp.data.source.repository.UserRepository
import com.weatherobserverdemoapp.data.source.repository.WeatherRepository
import com.weatherobserverdemoapp.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository
) :
    BaseViewModel() {

    private lateinit var loadUserDisposable: Disposable
    private lateinit var loadSelectedCitiesDisposable: Disposable
    private lateinit var loadWeatherDisposable: Disposable

    val instructionsVisibility = MutableLiveData<Int>()
    val instructionsText = MutableLiveData<Int>()
    val addImage = MutableLiveData<Int>()
    val addImageVisibility = MutableLiveData<Int>()
    val userName = MutableLiveData<String>()
    val userSelectedVisibility = MutableLiveData<Int>()

    var userId = 0
    var selectedCities = listOf<SelectedCity>()
    var loadingWeather = false

    override fun onCleared() {
        super.onCleared()
        if (::loadUserDisposable.isInitialized) loadUserDisposable.dispose()
        if (::loadSelectedCitiesDisposable.isInitialized) loadSelectedCitiesDisposable.dispose()
        if (::loadWeatherDisposable.isInitialized) loadWeatherDisposable.dispose()
    }

    fun bind(userId: Int) {
        this.userId = userId

        if (userId > 0) {
            instructionsText.value = R.string.add_cities
            instructionsVisibility.value = View.VISIBLE
            addImageVisibility.value = View.GONE
            userSelectedVisibility.value = View.VISIBLE
            loadUser(userId)
            loadSelectedCities(userId)
        } else {
            instructionsText.value = R.string.select_user
            instructionsVisibility.value = View.VISIBLE
            userSelectedVisibility.value = View.GONE
            addImageVisibility.value = View.VISIBLE
            addImage.value = R.drawable.baseline_add_circle_24
        }
    }

    private fun loadSelectedCities(userId: Int) {
        loadSelectedCitiesDisposable =
            cityRepository.selectedCityAvailable(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    selectedCities = it
                    loadCurrentWeather()
                    loadSelectedCitiesDisposable.dispose()
                }, {
                    loadSelectedCitiesDisposable.dispose()
                })
    }

    private fun loadUser(userId: Int) {
        loadUserDisposable = userRepository.loadUser(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userName.value = it.name
                loadUserDisposable.dispose()
            }, {
                loadUserDisposable.dispose()
            })
    }

    fun loadCurrentWeather() {
        if (loadingWeather) return
        loadingWeather = true

        loadCurrentWeather(0)
    }

    private fun loadCurrentWeather(position: Int) {
        if (position >= selectedCities.size) return
        loadWeatherDisposable =
            weatherRepository.getCurrentWeather(selectedCities[position].city.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val cityCurrentWeather = CityWeather(
                        city = selectedCities[position].city,
                        currentWeather = it.first()
                    )

                    loadCurrentWeather(position + 1)
                }, {
                    Timber.d(it)
                })
    }
}