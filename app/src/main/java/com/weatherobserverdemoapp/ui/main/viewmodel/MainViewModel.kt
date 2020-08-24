package com.weatherobserverdemoapp.ui.main.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.weatherobserverdemoapp.R
import com.weatherobserverdemoapp.data.model.CityCurrentWeather
import com.weatherobserverdemoapp.data.model.CityWeather
import com.weatherobserverdemoapp.data.model.SelectedCity
import com.weatherobserverdemoapp.data.source.repository.CityRepository
import com.weatherobserverdemoapp.data.source.repository.UserRepository
import com.weatherobserverdemoapp.data.source.repository.WeatherRepository
import com.weatherobserverdemoapp.ui.base.BaseViewModel
import com.weatherobserverdemoapp.utils.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
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
    private var selectedCities = listOf<SelectedCity>()
    var loadingWeather = false

    val currentWeatherLiveEvent = MutableLiveData<Event<CityWeather>>()
    val clearListLiveEvent = MutableLiveData<Event<Boolean>>()
    val endLoadingWeatherLiveEvent = MutableLiveData<Event<Boolean>>()
    val showMessageLiveEvent = MutableLiveData<Event<Int>>()

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

            clearListLiveEvent.value = Event(true)
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

    fun loadSelectedCities(userId: Int) {
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
                userName.value = it.email
                loadUserDisposable.dispose()
            }, {
                loadUserDisposable.dispose()
            })
    }

    private fun loadCurrentWeather() {
        if (loadingWeather) return
        loadingWeather = true

        loadCurrentWeather(0)
    }

    private fun loadCurrentWeather(position: Int) {
        if (position >= selectedCities.size) {
            loadingWeather = false
            endLoadingWeatherLiveEvent.value = Event(true)
            return
        }
        loadWeatherDisposable =
            weatherRepository.getCurrentWeather(selectedCities[position].city.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onLoadCityCurrentWeatherSuccess(it.first(), position)
                    loadCurrentWeather(position + 1)
                }, {
                    Timber.d(it)
                    loadingWeather = false
                    endLoadingWeatherLiveEvent.value = Event(true)
                    if (it is HttpException && it.code() == 503) {
                        showMessageLiveEvent.value = Event(R.string.unauthorized)
                    }
                })
    }

    private fun onLoadCityCurrentWeatherSuccess(
        currentWeather: CityCurrentWeather,
        cityPosition: Int
    ) {
        instructionsVisibility.value = View.GONE
        val cityWeather = CityWeather(
            selectedCity = selectedCities[cityPosition],
            currentWeather = currentWeather
        )
        currentWeatherLiveEvent.value = Event(cityWeather)
    }
}