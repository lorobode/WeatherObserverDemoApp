package com.weatherobserverdemoapp.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.weatherobserverdemoapp.data.model.City
import com.weatherobserverdemoapp.data.model.SelectedCity
import com.weatherobserverdemoapp.data.source.repository.CityRepository
import com.weatherobserverdemoapp.ui.base.BaseViewModel
import com.weatherobserverdemoapp.utils.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CityViewModel @Inject constructor(private val cityRepository: CityRepository) :
    BaseViewModel() {

    private lateinit var loadCitiesDisposable: Disposable
    private lateinit var addSelectedCityDisposable: Disposable

    val listCitiesLiveEvent = MutableLiveData<Event<List<City>>>()
    val cityLoadingLiveEvent = MutableLiveData<Event<Boolean>>()
    val cityAddedLiveEvent = MutableLiveData<Event<Boolean>>()
    val selectedCityEvent = MutableLiveData<Event<Pair<City, Boolean>>>()

    override fun onCleared() {
        super.onCleared()
        if (::loadCitiesDisposable.isInitialized) loadCitiesDisposable.dispose()
    }

    fun getCities(search: String) {
        loadCitiesDisposable =
            cityRepository.getCities(search).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    cityLoadingLiveEvent.postValue(Event(true))
                }
                .subscribe({
                    listCitiesLiveEvent.value = Event(it)
                }, {
                    Timber.d(it)
                    cityLoadingLiveEvent.value = Event(false)
                })
    }

    fun selectCity(city: City) {
        addSelectedCityDisposable =
            cityRepository.cityAdded(city.id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ cityRows ->
                    addSelectedCityDisposable.dispose()
                    selectedCityEvent.value = Event(Pair(city, cityRows > 0))
                }, {
                    Timber.d(it)
                    addSelectedCityDisposable.dispose()
                })
    }

    fun addSelectedCity(selectedCity: SelectedCity) {
        addSelectedCityDisposable =
            cityRepository.addSelectedCity(selectedCity).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    addSelectedCityDisposable.dispose()
                    cityAddedLiveEvent.value = Event(true)
                }, {
                    addSelectedCityDisposable.dispose()
                    Timber.d(it)
                    cityAddedLiveEvent.value = Event(false)
                })
    }
}