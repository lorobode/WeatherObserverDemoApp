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
import com.weatherobserverdemoapp.databinding.ActivityCityBinding
import com.weatherobserverdemoapp.ui.base.BaseActivity
import com.weatherobserverdemoapp.ui.main.adapter.CityAdapter
import com.weatherobserverdemoapp.ui.main.viewmodel.CityViewModel
import com.weatherobserverdemoapp.ui.main.viewmodel.CityViewModelFactory
import com.weatherobserverdemoapp.utils.extensions.searchWithDelay
import kotlinx.android.synthetic.main.activity_city.*

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
            mViewModel.getCities(it)
        }

        setupItemDecorator()
        cityList.adapter = adapter
    }


    private fun onUserClick(city: City?) {
        Toast.makeText(this, city?.name, Toast.LENGTH_LONG).show()
    }

    private fun setupItemDecorator() {
        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.list_divider)!!)
        cityList.addItemDecoration(itemDecorator)
    }
}