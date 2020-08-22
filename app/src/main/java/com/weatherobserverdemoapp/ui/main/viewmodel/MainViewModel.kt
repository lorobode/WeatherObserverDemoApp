package com.weatherobserverdemoapp.ui.main.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.weatherobserverdemoapp.R
import com.weatherobserverdemoapp.data.source.repository.UserRepository
import com.weatherobserverdemoapp.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    private lateinit var loadUserDisposable: Disposable

    val addUserMessageVisibility = MutableLiveData<Int>()
    val addImage = MutableLiveData<Int>()
    val addImageVisibility = MutableLiveData<Int>()
    val userName = MutableLiveData<String>()
    val userNameVisibility = MutableLiveData<Int>()

    var userId = 0

    override fun onCleared() {
        super.onCleared()
        if (::loadUserDisposable.isInitialized) loadUserDisposable.dispose()
    }

    fun bind(userId: Int) {
        this.userId = userId

        if (userId > 0) {
            addUserMessageVisibility.value = View.GONE
            addImageVisibility.value = View.GONE
            userNameVisibility.value = View.VISIBLE
            loadUser(userId)
        } else {
            addUserMessageVisibility.value = View.VISIBLE
            userNameVisibility.value = View.GONE
            addImageVisibility.value = View.VISIBLE
            addImage.value = R.drawable.baseline_add_circle_24
        }
    }

    private fun loadUser(userId: Int) {
        loadUserDisposable = userRepository.loadUser(userId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userName.value = it.name
                loadUserDisposable.dispose()
            }, {
                loadUserDisposable.dispose()
            })
    }
}