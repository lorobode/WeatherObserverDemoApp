package com.weatherobserverdemoapp.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.weatherobserverdemoapp.data.model.City
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

    val listCitiesLiveEvent = MutableLiveData<Event<List<City>>>()
    val cityLoadingLiveEvent = MutableLiveData<Event<Boolean>>()

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
}