package com.webprog26.coroutinesplaygroundapp.users.data_source

import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.User

interface DataProvider {
    suspend fun getUsersList(): List<User>
}