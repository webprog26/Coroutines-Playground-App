package com.webprog26.coroutinesplaygroundapp.users.data_source.data_model

import com.webprog26.coroutinesplaygroundapp.users.data_source.DataProvider

class NetworkDataProvider: DataProvider {

    override suspend fun getUsersList(): List<User> {
        return listOf()
    }
}