package com.weatherobserverdemoapp.utils.extensions

import java.text.SimpleDateFormat
import java.util.*

fun fromOnlyDate(stringDate: String): Date? {
    if (stringDate.isEmpty()) return null
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.parse(stringDate)
}

fun Date.formatToOnlyDate(): String? {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(this)
}

fun Date.formatToDateTime(): String? {
    val sdf = SimpleDateFormat("HH:mm - dd/MM/yyyy", Locale.getDefault())
    return sdf.format(this)
}