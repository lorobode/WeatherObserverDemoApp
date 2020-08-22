package com.weatherobserverdemoapp.utils

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.weatherobserverdemoapp.utils.extensions.getParentActivity

@BindingAdapter("mutableReferenceImage")
fun setMutableReferenceImage(view: AppCompatImageView, img: MutableLiveData<Int?>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && img != null) {
        img.observe(parentActivity, Observer { value ->
            value?.let {
                view.setImageResource(value)
            }
        })
    }
}

@BindingAdapter("mutableReferenceText")
fun setMutableReferenceText(view: TextView, text: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value ->
            value?.let {
                if (it == 0) view.text = ""
                else view.text = parentActivity.getText(it)
            }
        })
    }
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(
            parentActivity,
            Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value ->
            view.text = value ?: ""
        })
    }
}