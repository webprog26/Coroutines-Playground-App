package com.webprog26.coroutinesplaygroundapp.data_source

import com.webprog26.coroutinesplaygroundapp.data_source.data_model.User

interface DataProvider {
    suspend fun getUsersList(): List<User>
}