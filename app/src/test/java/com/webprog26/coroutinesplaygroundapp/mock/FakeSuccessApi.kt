package com.webprog26.coroutinesplaygroundapp.mock

import com.webprog26.coroutinesplaygroundapp.FakeLocalDataSuccessProvider
import com.webprog26.coroutinesplaygroundapp.data_source.data_model.User
import com.webprog26.coroutinesplaygroundapp.network.UsersApi

class FakeSuccessApi: UsersApi {

    override suspend fun getUsers(): List<User> {
        return FakeLocalDataSuccessProvider.USERS_LIST
    }
}