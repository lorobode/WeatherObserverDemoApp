package com.weatherobserverdemoapp.data.source.repository

import com.weatherobserverdemoapp.data.model.User
import com.weatherobserverdemoapp.data.source.local.dao.UserDao
import io.reactivex.Observable
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    fun getUsers(): Observable<List<User>> {
        return userDao.getAll().toObservable()
    }

    fun addUser(user: User): Observable<List<Long>> {
        return userDao.add(user).toObservable()
    }

    fun loadUser(userId: Int): Observable<User> {
        return userDao.loadById(userId).toObservable()
    }
}

