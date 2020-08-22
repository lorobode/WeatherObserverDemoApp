package com.weatherobserverdemoapp.utils.extensions

import android.content.Context
import androidx.preference.PreferenceManager

private const val USER_ID = "data.source.prefs.USER_ID"

fun Context.setUserId(userId: Int) {
    PreferenceManager.getDefaultSharedPreferences(this).edit()
        .putInt(USER_ID, userId)
        .apply()
}

fun Context.getUserId(): Int {
    return PreferenceManager.getDefaultSharedPreferences(this).getInt(USER_ID, 0)
}