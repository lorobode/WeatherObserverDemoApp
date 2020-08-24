package com.weatherobserverdemoapp.ui.main.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.weatherobserverdemoapp.R
import com.weatherobserverdemoapp.databinding.ActivityMainBinding
import com.weatherobserverdemoapp.ui.base.BaseActivity
import com.weatherobserverdemoapp.ui.main.adapter.CurrentWeatherAdapter
import com.weatherobserverdemoapp.ui.main.viewmodel.MainViewModel
import com.weatherobserverdemoapp.ui.main.viewmodel.MainViewModelFactory
import com.weatherobserverdemoapp.utils.extensions.getUserId
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel, MainViewModelFactory>() {

    // attributes

    private val adapter by lazy { CurrentWeatherAdapter() }

    // BaseActivity

    private lateinit var mBinding: ActivityMainBinding

    override fun getLayoutId() = R.layout.activity_main

    override fun getViewModelClass() = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mBinding.viewModel = mViewModel
        mViewModel.bind(getUserId())

        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)

        initUi()

        mViewModel.currentWeatherLiveEvent.observe(this, Observer {
            it.getContentIfNotHandledOrReturnNull()?.let { cityWeather ->
                adapter.addCurrentWeather(cityWeather)
            }
        })

        mViewModel.clearListLiveEvent.observe(this, Observer {
            it.getContentIfNotHandledOrReturnNull()?.let { clear ->
                if (clear) adapter.clear()
            }
        })

        mViewModel.endLoadingWeatherLiveEvent.observe(this, Observer {
            it.getContentIfNotHandledOrReturnNull()?.let { endLoading ->
                if (endLoading) swipeContainer.isRefreshing = false
            }
        })

        mViewModel.showMessageLiveEvent.observe(this, Observer {
            it.getContentIfNotHandledOrReturnNull()?.let { messageRef ->
                Toast.makeText(this, messageRef, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()

        val currentUserId = getUserId()
        if (mViewModel.userId != currentUserId) mViewModel.bind(currentUserId)
    }

    private fun initUi() {
        setupItemDecorator()
        currentWeatherList.adapter = adapter

        userContainer.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }

        addCityFab.setOnClickListener {
            startActivity(Intent(this, CityActivity::class.java))
        }

        swipeContainer.setOnRefreshListener {
            adapter.clear()
            mViewModel.loadSelectedCities(getUserId())
        }
    }

    private fun setupItemDecorator() {
        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.list_divider)!!)
        currentWeatherList.addItemDecoration(itemDecorator)
    }
}