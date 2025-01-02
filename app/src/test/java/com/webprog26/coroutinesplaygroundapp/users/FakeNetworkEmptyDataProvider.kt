package com.webprog26.coroutinesplaygroundapp.users

import com.webprog26.coroutinesplaygroundapp.users.data_source.DataProvider
import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.User

class FakeNetworkEmptyDataProvider : DataProvider {

    override suspend fun getUsersList(): List<User> {
        return listOf()
    }
}