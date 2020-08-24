package com.weatherobserverdemoapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.weatherobserverdemoapp.R
import com.weatherobserverdemoapp.data.model.CityWeather
import com.weatherobserverdemoapp.databinding.ItemCurrentWeatherBinding
import com.weatherobserverdemoapp.ui.main.viewmodel.CurrentWeatherItemViewModel


class CurrentWeatherAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val currentWeathers = mutableListOf<CityWeather>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CityWeatherHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_current_weather,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CityWeatherHolder) {
            holder.bind(currentWeathers, position)
        }
    }

    override fun getItemCount() = currentWeathers.size

    fun addCurrentWeather(cityWeather: CityWeather) {
        currentWeathers.add(0, cityWeather)
        notifyItemInserted(0)
    }

    fun clear() {
        currentWeathers.clear()
        notifyDataSetChanged()
    }

    abstract class DefaultHolder(view: View) : RecyclerView.ViewHolder(view)

    class CityWeatherHolder(private val binding: ItemCurrentWeatherBinding) :
        DefaultHolder(binding.root) {

        private val viewModel = CurrentWeatherItemViewModel()

        fun bind(options: List<CityWeather>, position: Int) {
            binding.viewModel = viewModel
            viewModel.bind(options[position])
        }
    }
}