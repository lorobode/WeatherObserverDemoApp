package com.weatherobserverdemoapp.ui.main.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.weatherobserverdemoapp.R
import com.weatherobserverdemoapp.data.model.City
import com.weatherobserverdemoapp.data.model.SelectedCity
import com.weatherobserverdemoapp.databinding.ActivityCityBinding
import com.weatherobserverdemoapp.ui.base.BaseActivity
import com.weatherobserverdemoapp.ui.main.adapter.CityAdapter
import com.weatherobserverdemoapp.ui.main.dialog.SelectCityDialog
import com.weatherobserverdemoapp.ui.main.viewmodel.CityViewModel
import com.weatherobserverdemoapp.ui.main.viewmodel.CityViewModelFactory
import com.weatherobserverdemoapp.utils.extensions.getUserId
import com.weatherobserverdemoapp.utils.extensions.searchWithDelay
import kotlinx.android.synthetic.main.activity_city.*
import java.util.*

class CityActivity : BaseActivity<CityViewModel, CityViewModelFactory>() {

    // attributes

    private val adapter by lazy { CityAdapter(::onUserClick) }

    // BaseActivity

    private lateinit var mBinding: ActivityCityBinding

    override fun getLayoutId() = R.layout.activity_city

    override fun getViewModelClass() = CityViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mBinding.viewModel = mViewModel

        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.city_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initUi()

        mViewModel.listCitiesLiveEvent.observe(this, Observer {
            it.getContentIfNotHandledOrReturnNull()?.let { cities ->
                adapter.addCities(cities)
            }
        })

        mViewModel.cityLoadingLiveEvent.observe(this, Observer {
            it.getContentIfNotHandledOrReturnNull()?.let { loading ->
                adapter.setLoading(loading)
            }
        })

        mViewModel.selectedCityEvent.observe(this, Observer {
            it.getContentIfNotHandledOrReturnNull()?.let { cityUpdate ->
                startSelectCityDialog(cityUpdate.first, cityUpdate.second)
            }
        })

        mViewModel.cityAddedLiveEvent.observe(this, Observer {
            it.getContentIfNotHandledOrReturnNull()?.let { added ->
                val message = if (added) R.string.city_added else R.string.city_error
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    // Private fun

    private fun initUi() {
        searchCityEdt.searchWithDelay {
            runOnUiThread { adapter.clear() }
            if (it.isNotEmpty()) mViewModel.getCities(it)
        }

        setupItemDecorator()
        cityList.adapter = adapter
    }


    private fun onUserClick(city: City) {
        mViewModel.selectCity(city)
    }

    private fun setupItemDecorator() {
        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.list_divider)!!)
        cityList.addItemDecoration(itemDecorator)
    }

    private fun startSelectCityDialog(city: City, update: Boolean) {
        val title = if (update) R.string.update_city else R.string.add_city
        val selectCityDialog = SelectCityDialog(title, city) { from, until ->

            if (from != null && until != null) {
                adapter.clear()
                searchCityEdt.setText("")

                // Add 23:59:59 to until date
                val finalUntilDate = Date(until.time + (1000 * 60 * 60 * 24) - 1000)

                val selectedCity = SelectedCity(
                    city = city,
                    userId = getUserId(),
                    windowStart = from,
                    windowEnd = finalUntilDate
                )

                mViewModel.addSelectedCity(selectedCity)
            }
        }

        selectCityDialog.show(supportFragmentManager, null)
    }
}