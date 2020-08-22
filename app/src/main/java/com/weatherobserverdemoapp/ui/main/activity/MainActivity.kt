package com.weatherobserverdemoapp.ui.main.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.weatherobserverdemoapp.R
import com.weatherobserverdemoapp.databinding.ActivityMainBinding
import com.weatherobserverdemoapp.ui.base.BaseActivity
import com.weatherobserverdemoapp.ui.main.viewmodel.MainViewModel
import com.weatherobserverdemoapp.ui.main.viewmodel.MainViewModelFactory

class MainActivity : BaseActivity<MainViewModel, MainViewModelFactory>() {

    private lateinit var mBinding: ActivityMainBinding

    override fun getLayoutId() = R.layout.activity_main

    override fun getViewModelClass() = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mBinding.viewModel = mViewModel
    }
}