package com.webprog26.coroutinesplaygroundapp.users

import com.webprog26.coroutinesplaygroundapp.users.data_source.DataUpdater
import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.User

class FakeLocalDataUpdater: DataUpdater {

    override suspend fun updateUsers(users: List<User>) {
    }
}