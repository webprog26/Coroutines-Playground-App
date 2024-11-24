package com.webprog26.coroutinesplaygroundapp

import com.webprog26.coroutinesplaygroundapp.data_source.DataProvider
import com.webprog26.coroutinesplaygroundapp.data_source.data_model.User
import com.webprog26.coroutinesplaygroundapp.network.UsersApi

class FakeNetworkDataProvider(private val api: UsersApi): DataProvider {

    override suspend fun getUsersList(): List<User> {
        return api.getUsers()
    }
}