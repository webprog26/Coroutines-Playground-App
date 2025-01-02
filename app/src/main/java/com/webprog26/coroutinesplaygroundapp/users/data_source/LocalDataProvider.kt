package com.webprog26.coroutinesplaygroundapp.users.data_source

import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.User
import com.webprog26.coroutinesplaygroundapp.users.data_source.database.UsersDatabase

class LocalDataProvider(private val usersDatabase: UsersDatabase): DataProvider {

    override suspend fun getUsersList(): List<User> {
        return usersDatabase.usersDao().getUsers()
    }
}