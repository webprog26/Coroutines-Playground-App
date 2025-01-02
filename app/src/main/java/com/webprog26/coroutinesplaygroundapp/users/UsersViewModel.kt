package com.webprog26.coroutinesplaygroundapp.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.webprog26.coroutinesplaygroundapp.users.data_source.DataSource
import kotlinx.coroutines.launch

class UsersViewModel(
    private val dataSource: DataSource
) : ViewModel() {

    private val _usersListData: MutableLiveData<UiState> = MutableLiveData()
    val usersListData: LiveData<UiState>
        get() = _usersListData

    fun loadUsers() {

        viewModelScope.launch {
            dataSource.loadUsers {uiState ->
                _usersListData.value = uiState
            }
        }
    }
}