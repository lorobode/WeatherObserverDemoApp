package com.weatherobserverdemoapp.ui.main.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.weatherobserverdemoapp.R
import com.weatherobserverdemoapp.databinding.ActivityUserBinding
import com.weatherobserverdemoapp.ui.base.BaseActivity
import com.weatherobserverdemoapp.ui.main.viewmodel.UserViewModel
import com.weatherobserverdemoapp.ui.main.viewmodel.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : BaseActivity<UserViewModel, UserViewModelFactory>() {

    private lateinit var mBinding: ActivityUserBinding

    override fun getLayoutId() = R.layout.activity_user

    override fun getViewModelClass() = UserViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mBinding.viewModel = mViewModel

        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.user_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}