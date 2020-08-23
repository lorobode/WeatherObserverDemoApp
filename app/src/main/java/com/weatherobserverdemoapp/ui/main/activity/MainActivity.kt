package com.weatherobserverdemoapp.ui.main.activity

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.weatherobserverdemoapp.R
import com.weatherobserverdemoapp.databinding.ActivityMainBinding
import com.weatherobserverdemoapp.ui.base.BaseActivity
import com.weatherobserverdemoapp.ui.main.viewmodel.MainViewModel
import com.weatherobserverdemoapp.ui.main.viewmodel.MainViewModelFactory
import com.weatherobserverdemoapp.utils.extensions.getUserId
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel, MainViewModelFactory>() {

    private lateinit var mBinding: ActivityMainBinding

    override fun getLayoutId() = R.layout.activity_main

    override fun getViewModelClass() = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mBinding.viewModel = mViewModel
        mViewModel.bind(getUserId())

        initUi()
    }

    override fun onResume() {
        super.onResume()

        val currentUserId = getUserId()
        if (mViewModel.userId != currentUserId) mViewModel.bind(currentUserId)
    }

    private fun initUi() {
        userContainer.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }

        addCityFab.setOnClickListener {
            startActivity(Intent(this, CityActivity::class.java))
        }
    }
}