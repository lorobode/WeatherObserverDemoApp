package com.weatherobserverdemoapp.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel, F : ViewModelProvider.Factory> :
    AppCompatActivity() {

    @Inject
    lateinit var mViewModelFactory: F
    lateinit var mViewModel: VM

    @LayoutRes
    abstract fun getLayoutId(): Int

    protected abstract fun getViewModelClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        mViewModel = ViewModelProvider(this, mViewModelFactory).get(getViewModelClass())

        super.onCreate(savedInstanceState)
    }

}