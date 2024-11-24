package com.webprog26.coroutinesplaygroundapp.data_source

import com.webprog26.coroutinesplaygroundapp.data_source.data_model.User
import com.webprog26.coroutinesplaygroundapp.network.UsersApi

class NetworkDataProvider(private  val api: UsersApi): DataProvider {

    override suspend fun getUsersList(): List<User> {
        return api.getUsers()
    }
}