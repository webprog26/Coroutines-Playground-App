package com.webprog26.coroutinesplaygroundapp.users.data_source

import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.User
import com.webprog26.coroutinesplaygroundapp.users.network.UsersApi

class NetworkDataProvider(private  val api: UsersApi): DataProvider {

    override suspend fun getUsersList(): List<User> {
        return api.getUsers()
    }
}