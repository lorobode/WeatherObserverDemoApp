package com.weatherobserverdemoapp.ui.main.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.weatherobserverdemoapp.R
import com.weatherobserverdemoapp.data.model.City
import com.weatherobserverdemoapp.utils.extensions.formatToOnlyDate
import com.weatherobserverdemoapp.utils.extensions.fromOnlyDate
import com.weatherobserverdemoapp.utils.extensions.showDatePicker
import java.util.*

class SelectCityDialog(
    private val title: Int,
    val city: City,
    val confirmAction: (Date?, Date?) -> Unit
) : DialogFragment() {

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        val view: View? = activity?.layoutInflater?.inflate(R.layout.dialog_select_city, null)
        val alert = AlertDialog.Builder(requireActivity(), R.style.MyDialogTheme)
        alert.setView(view)
        alert.setTitle(title)

        val cityName = view?.findViewById<TextView>(R.id.cityName)
        val fromDate = view?.findViewById<TextView>(R.id.fromDate)
        val untilDate = view?.findViewById<TextView>(R.id.untilDate)

        val fromDateContainer = view?.findViewById<LinearLayout>(R.id.fromDateContainer)
        val untilDateContainer = view?.findViewById<LinearLayout>(R.id.untilDateContainer)

        cityName?.text = city.name
        fromDate?.text = Date().formatToOnlyDate()

        fromDateContainer?.setOnClickListener {
            activity?.showDatePicker(
                selectedDateString = fromDate?.text.toString(),
                minDate = Date().time
            ) {
                fromDate?.text = it?.formatToOnlyDate()

                // Clear untilDate if needed
                if (it != null && it > fromOnlyDate(untilDate?.text.toString())) {
                    untilDate?.text = ""
                }
            }
        }
        untilDateContainer?.setOnClickListener {
            activity?.showDatePicker(
                selectedDateString = untilDate?.text.toString(),
                minDate = fromOnlyDate(fromDate?.text.toString())?.time
            ) {
                untilDate?.text = it?.formatToOnlyDate()
            }
        }

        alert.setPositiveButton(R.string.confirm_button) { _, _ ->
            val windowFrom = fromOnlyDate(fromDate?.text.toString())
            val windowUntil = fromOnlyDate(untilDate?.text.toString())

            confirmAction(windowFrom, windowUntil)
        }

        alert.setNegativeButton(R.string.cancel_button) { alertDialog, _ ->
            alertDialog.dismiss()
        }

        return alert.create()
    }
}