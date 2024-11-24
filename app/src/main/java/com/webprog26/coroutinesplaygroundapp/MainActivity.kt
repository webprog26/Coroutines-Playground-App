package com.webprog26.coroutinesplaygroundapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.webprog26.coroutinesplaygroundapp.data_source.DataSource
import com.webprog26.coroutinesplaygroundapp.data_source.LocalDataProvider
import com.webprog26.coroutinesplaygroundapp.data_source.LocalDataUpdater
import com.webprog26.coroutinesplaygroundapp.data_source.NetworkDataProvider
import com.webprog26.coroutinesplaygroundapp.data_source.database.UsersDatabase
import com.webprog26.coroutinesplaygroundapp.network.createUsersApi

class MainActivity : AppCompatActivity() {

    private val adapter = UsersAdapter()

    private val viewModel: UsersViewModel by viewModels {
        ViewModelFactory(DataSource(LocalDataProvider(UsersDatabase.getInstance(applicationContext)), NetworkDataProvider(
            createUsersApi()
        ), LocalDataUpdater((UsersDatabase.getInstance(applicationContext)))))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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
}