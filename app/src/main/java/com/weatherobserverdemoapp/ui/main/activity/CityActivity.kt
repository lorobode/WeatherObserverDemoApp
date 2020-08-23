package com.weatherobserverdemoapp.ui.main.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.weatherobserverdemoapp.R
import com.weatherobserverdemoapp.databinding.ActivityCityBinding
import com.weatherobserverdemoapp.ui.base.BaseActivity
import com.weatherobserverdemoapp.ui.main.viewmodel.CityViewModel
import com.weatherobserverdemoapp.ui.main.viewmodel.CityViewModelFactory
import com.weatherobserverdemoapp.utils.extensions.searchWithDelay
import kotlinx.android.synthetic.main.activity_city.*
import kotlinx.android.synthetic.main.activity_user.toolbar

class CityActivity : BaseActivity<CityViewModel, CityViewModelFactory>() {

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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initUi() {
        searchCityEdt.searchWithDelay {
            runOnUiThread { Toast.makeText(this, it, Toast.LENGTH_LONG).show() }
        }
    }
}