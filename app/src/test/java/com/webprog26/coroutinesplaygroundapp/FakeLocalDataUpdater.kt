package com.webprog26.coroutinesplaygroundapp

import com.webprog26.coroutinesplaygroundapp.data_source.DataUpdater
import com.webprog26.coroutinesplaygroundapp.data_source.data_model.User

class FakeLocalDataUpdater: DataUpdater {

    override suspend fun updateUsers(users: List<User>) {
    }
}