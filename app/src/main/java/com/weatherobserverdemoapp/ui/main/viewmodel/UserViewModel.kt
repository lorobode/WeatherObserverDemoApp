package com.weatherobserverdemoapp.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.weatherobserverdemoapp.data.model.User
import com.weatherobserverdemoapp.data.source.repository.UserRepository
import com.weatherobserverdemoapp.ui.base.BaseViewModel
import com.weatherobserverdemoapp.utils.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    private lateinit var addUserDisposable: Disposable
    private lateinit var listUsersDisposable: Disposable

    val addUserLiveEvent = MutableLiveData<Event<User>>()
    val listUsersLiveEvent = MutableLiveData<Event<List<User>>>()

    override fun onCleared() {
        super.onCleared()
        if (::addUserDisposable.isInitialized) addUserDisposable.dispose()
        if (::listUsersDisposable.isInitialized) listUsersDisposable.dispose()
    }

    init {

    }

    fun loadUsers() {
        listUsersDisposable = userRepository.getUsers().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listUsersLiveEvent.value = Event(it)
                listUsersDisposable.dispose()
            }, {
                listUsersDisposable.dispose()
            })
    }

    fun addUser(email: String) {
        val user = User(email = email)
        addUserDisposable = userRepository.addUser(user).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                user.id = it.first().toInt()
                addUserLiveEvent.value = Event(user)
                addUserDisposable.dispose()
            }, {
                addUserDisposable.dispose()
            })
    }
}