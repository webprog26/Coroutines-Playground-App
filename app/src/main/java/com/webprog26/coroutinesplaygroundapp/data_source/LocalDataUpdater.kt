package com.webprog26.coroutinesplaygroundapp.data_source

import com.webprog26.coroutinesplaygroundapp.data_source.data_model.User
import com.webprog26.coroutinesplaygroundapp.data_source.database.UsersDatabase

class LocalDataUpdater(private val usersDatabase: UsersDatabase): DataUpdater {

    override suspend fun updateUsers(users: List<User>) {
        users.forEach { user ->
            usersDatabase.usersDao().insert(user)
        }
    }
}