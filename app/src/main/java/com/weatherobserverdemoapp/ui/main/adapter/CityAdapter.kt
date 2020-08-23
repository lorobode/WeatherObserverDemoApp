package com.weatherobserverdemoapp.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.weatherobserverdemoapp.R
import com.weatherobserverdemoapp.data.model.City
import com.weatherobserverdemoapp.databinding.ItemCityBinding
import com.weatherobserverdemoapp.ui.main.viewmodel.CityItemViewModel
import com.weatherobserverdemoapp.utils.holder.LoadingHolder


class CityAdapter(
    private var clickListener: (City?) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_LOADING = 0
        private const val VIEW_TYPE_CITY = 1
    }

    private val cities = mutableListOf<City>()

    private var loading = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == VIEW_TYPE_CITY) TextHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_city,
                parent,
                false
            ), parent.context, clickListener
        ) else LoadingHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TextHolder) {
            holder.bind(cities, position)
        }
    }

    override fun getItemCount() = if (cities.isEmpty() && loading) 1 else cities.size

    override fun getItemViewType(position: Int): Int {
        return if (cities.isEmpty())
            VIEW_TYPE_LOADING
        else
            VIEW_TYPE_CITY
    }

    fun addCities(list: List<City>) {
        loading = false
        cities.addAll(list)
        notifyDataSetChanged()
    }

    fun setLoading(loading: Boolean) {
        this.loading = loading
        notifyDataSetChanged()
    }

    abstract class DefaultHolder(view: View) : RecyclerView.ViewHolder(view)

    class TextHolder(
        private val binding: ItemCityBinding,
        private val context: Context,
        val clickListener: (City?) -> Unit
    ) : DefaultHolder(binding.root) {

        private val viewModel = CityItemViewModel()

        fun bind(options: List<City>, position: Int) {
            binding.viewModel = viewModel

            val city = options[position]
            viewModel.bind(city)

            itemView.setOnClickListener { clickListener(city) }
        }
    }
}