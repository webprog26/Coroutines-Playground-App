package com.webprog26.coroutinesplaygroundapp.users

import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.User

sealed class UiState {
    data class Success(val users: List<User>): UiState()

    data class Error(val message: String): UiState()
}