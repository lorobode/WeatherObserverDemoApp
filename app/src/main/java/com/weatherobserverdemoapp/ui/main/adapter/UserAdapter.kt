package com.weatherobserverdemoapp.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.weatherobserverdemoapp.R
import com.weatherobserverdemoapp.data.model.User
import com.weatherobserverdemoapp.databinding.ItemUserBinding
import com.weatherobserverdemoapp.ui.main.viewmodel.UserItemViewModel


class UserAdapter(
    private var clickListener: (User?) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_ADD = 0
        private const val VIEW_TYPE_USER = 1
    }

    private val allUsers = mutableListOf<User>()
    private var filteredUsers = listOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TextHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_user,
                parent,
                false
            ), parent.context, clickListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TextHolder) {
            holder.bind(filteredUsers, position)
        }
    }

    override fun getItemCount() = if (filteredUsers.isEmpty()) 1 else filteredUsers.size

    override fun getItemViewType(position: Int): Int {
        return if (filteredUsers.isEmpty())
            VIEW_TYPE_ADD
        else
            VIEW_TYPE_USER
    }

    fun addUsers(list: List<User>) {
        allUsers.addAll(list)
        filteredUsers = allUsers
        notifyDataSetChanged()
    }

    fun addUser(user: User) {
        if (filteredUsers.contains(user)) return
        allUsers.add(0, user)
        filteredUsers = allUsers
        notifyDataSetChanged()
    }

    fun filterUsers(userName: String) {
        filteredUsers = allUsers.filter { it.email.toLowerCase().contains(userName.toLowerCase()) }
        notifyDataSetChanged()
    }

    abstract class DefaultHolder(view: View) : RecyclerView.ViewHolder(view)

    class TextHolder(
        private val binding: ItemUserBinding,
        private val context: Context,
        val clickListener: (User?) -> Unit
    ) : DefaultHolder(binding.root) {

        private val viewModel = UserItemViewModel()

        fun bind(options: List<User>, position: Int) {
            binding.viewModel = viewModel

            val user = if (options.isEmpty()) null else options[position]
            if (user == null) viewModel.bind(context.getString(R.string.add_user))
            else viewModel.bind(options[position].email)

            itemView.setOnClickListener { clickListener(user) }
        }
    }
}