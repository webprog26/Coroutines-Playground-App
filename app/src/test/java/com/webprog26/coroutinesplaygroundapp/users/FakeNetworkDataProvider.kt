package com.webprog26.coroutinesplaygroundapp.users

import com.webprog26.coroutinesplaygroundapp.users.data_source.DataProvider
import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.User
import com.webprog26.coroutinesplaygroundapp.users.network.UsersApi

class FakeNetworkDataProvider(private val api: UsersApi): DataProvider {

    override suspend fun getUsersList(): List<User> {
        return api.getUsers()
    }
}