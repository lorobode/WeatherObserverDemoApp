package com.weatherobserverdemoapp.ui.main.activity

import android.os.Bundle
import android.util.Patterns
import android.view.MenuItem
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.weatherobserverdemoapp.R
import com.weatherobserverdemoapp.data.model.User
import com.weatherobserverdemoapp.databinding.ActivityUserBinding
import com.weatherobserverdemoapp.ui.base.BaseActivity
import com.weatherobserverdemoapp.ui.main.adapter.UserAdapter
import com.weatherobserverdemoapp.ui.main.viewmodel.UserViewModel
import com.weatherobserverdemoapp.ui.main.viewmodel.UserViewModelFactory
import com.weatherobserverdemoapp.utils.extensions.setUserId
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : BaseActivity<UserViewModel, UserViewModelFactory>() {

    // attributes

    private val adapter by lazy { UserAdapter(::onUserClick) }

    // BaseActivity

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

        mViewModel.addUserLiveEvent.observe(this, Observer {
            it.getContentIfNotHandledOrReturnNull()?.let { user ->
                searchEdt.setText("")
                adapter.addUser(user)
            }
        })

        mViewModel.listUsersLiveEvent.observe(this, Observer {
            it.getContentIfNotHandledOrReturnNull()?.let { users ->
                adapter.addUsers(users)
            }
        })
        initUI()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initUI() {
        userList.adapter = adapter
        searchEdt.doOnTextChanged { text, _, _, _ ->
            adapter.filterUsers(text.toString().toLowerCase().replace(" ", ""))
        }

        mViewModel.loadUsers()
    }

    private fun onUserClick(user: User?) {
        val currentSearch = searchEdt.text.toString().toLowerCase().replace(" ", "")
        val validEmail = Patterns.EMAIL_ADDRESS.matcher(currentSearch).matches()

        if (user == null && currentSearch.isNotEmpty()) {
            if (validEmail) {
                mViewModel.addUser(currentSearch)
            } else {
                Toast.makeText(this, R.string.email_not_valid, Toast.LENGTH_LONG).show()
            }
        } else {
            user?.id?.let { setUserId(it) }
            finish()
        }
    }
}