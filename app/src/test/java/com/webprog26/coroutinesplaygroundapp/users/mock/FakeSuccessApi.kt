package com.webprog26.coroutinesplaygroundapp.users.mock

import com.webprog26.coroutinesplaygroundapp.users.FakeLocalDataSuccessProvider
import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.User
import com.webprog26.coroutinesplaygroundapp.users.network.UsersApi

class FakeSuccessApi: UsersApi {

    override suspend fun getUsers(): List<User> {
        return FakeLocalDataSuccessProvider.USERS_LIST
    }
}