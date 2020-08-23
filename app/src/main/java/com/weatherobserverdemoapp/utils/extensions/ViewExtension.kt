package com.weatherobserverdemoapp.utils.extensions

import android.annotation.SuppressLint
import android.content.ContextWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

fun View.getParentActivity(): AppCompatActivity? {
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

@SuppressLint("CheckResult")
fun AppCompatEditText.searchWithDelay(delay: Long = 1000, action: (String) -> Unit) {
    Observable.create(ObservableOnSubscribe<String> { subscriber ->
        this.addTextChangedListener {
            subscriber.onNext(it.toString())
        }
    }).subscribeOn(Schedulers.io())
        .map { text -> text.toLowerCase(Locale.getDefault()).trim() }
        .debounce(delay, TimeUnit.MILLISECONDS)
        .subscribe { text ->
            action(text)
        }
}