package com.webprog26.coroutinesplaygroundapp

import com.webprog26.coroutinesplaygroundapp.data_source.DataProvider
import com.webprog26.coroutinesplaygroundapp.data_source.data_model.User

class FakeNetworkEmptyDataProvider : DataProvider {

    override suspend fun getUsersList(): List<User> {
        return listOf()
    }
}