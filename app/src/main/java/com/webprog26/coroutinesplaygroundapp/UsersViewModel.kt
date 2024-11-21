package com.webprog26.coroutinesplaygroundapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.webprog26.coroutinesplaygroundapp.data_source.DataSource
import com.webprog26.coroutinesplaygroundapp.data_source.data_model.User
import kotlinx.coroutines.launch

class UsersViewModel(private val dataSource: DataSource): ViewModel() {

    private val _usersListData: MutableLiveData<List<User>> = MutableLiveData()
    val usersListData: LiveData<List<User>>
        get() = _usersListData

    fun loadUsers() {

        viewModelScope.launch {
            val users = dataSource.getUsersList();

            if (users.isNotEmpty()) {
                _usersListData.value = users
            }
        }
    }
}