package com.weatherobserverdemoapp.utils.extensions

import android.app.DatePickerDialog
import android.content.Context
import androidx.preference.PreferenceManager
import com.weatherobserverdemoapp.R
import java.text.SimpleDateFormat
import java.util.*

private const val USER_ID = "data.source.prefs.USER_ID"

fun Context.setUserId(userId: Int) {
    PreferenceManager.getDefaultSharedPreferences(this).edit()
        .putInt(USER_ID, userId)
        .apply()
}

fun Context.getUserId(): Int {
    return PreferenceManager.getDefaultSharedPreferences(this).getInt(USER_ID, 0)
}

fun Context.showDatePicker(
    selectedDateString: String,
    minDate: Long? = null,
    maxDate: Long? = null,
    onDateSelected: (Date?) -> Unit
) {

    val c = Calendar.getInstance()
    val selectedDate =
        if (selectedDateString.isNotEmpty())
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(selectedDateString)
        else null
    selectedDate?.let { c.time = it }

    val y = c.get(Calendar.YEAR)
    val m = c.get(Calendar.MONTH)
    val d = c.get(Calendar.DAY_OF_MONTH)

    val dpd = DatePickerDialog(
        this,
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            val dateString = "${String.format("%02d", dayOfMonth)}/${String.format(
                "%02d",
                monthOfYear + 1
            )}/$year"
            onDateSelected(fromOnlyDate(dateString))
        },
        y,
        m,
        d
    )

    dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, getString(R.string.ok_button), dpd)
    dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, getString(R.string.cancel_button), dpd)
    maxDate?.let { dpd.datePicker.maxDate = it }
    minDate?.let { dpd.datePicker.minDate = it }
    dpd.show()
}