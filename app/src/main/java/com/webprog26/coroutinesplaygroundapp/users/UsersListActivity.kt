package com.webprog26.coroutinesplaygroundapp.users

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.webprog26.coroutinesplaygroundapp.R
import com.webprog26.coroutinesplaygroundapp.users.data_source.DataSource
import com.webprog26.coroutinesplaygroundapp.users.data_source.LocalDataProvider
import com.webprog26.coroutinesplaygroundapp.users.data_source.LocalDataUpdater
import com.webprog26.coroutinesplaygroundapp.users.data_source.NetworkDataProvider
import com.webprog26.coroutinesplaygroundapp.users.data_source.database.UsersDatabase
import com.webprog26.coroutinesplaygroundapp.users.network.createUsersApi

class UsersListActivity : BaseActivity() {

    private val adapter = UsersAdapter()

    private val viewModel: UsersViewModel by viewModels {
        ViewModelFactory(DataSource(LocalDataProvider(UsersDatabase.getInstance(applicationContext)), NetworkDataProvider(
            createUsersApi()
        ), LocalDataUpdater((UsersDatabase.getInstance(applicationContext)))))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.usersListData.observe(this) {uiState ->
            when (uiState) {
                is UiState.Success -> {
                    adapter.usersList = uiState.users

                }

                is UiState.Error -> {
                    Toast.makeText(applicationContext, uiState.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.loadUsers()
    }

    override fun getLayoutRes() = R.layout.activity_users_list

    override fun getLayoutId() = R.id.users_list
}