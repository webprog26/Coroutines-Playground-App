package com.webprog26.coroutinesplaygroundapp.data_source

import com.webprog26.coroutinesplaygroundapp.UiState

open class DataSource(
    private val localDataProvider: DataProvider,
    private val networkDataProvider: DataProvider,
    private val localDataUpdater: DataUpdater
) {

    suspend fun loadUsers(onUsersDataUpdated: (UiState) -> Unit) {

        val localUsersData = localDataProvider.getUsersList()

        if (localUsersData.isNotEmpty()) {
            onUsersDataUpdated(UiState.Success(localUsersData))
        }

        try {
            val networkUsersData = networkDataProvider.getUsersList()
            if (networkUsersData.isNotEmpty()) {

                localDataUpdater.updateUsers(networkUsersData)

                onUsersDataUpdated(UiState.Success(networkUsersData))
            }

        } catch (e: Exception) {
            onUsersDataUpdated(UiState.Error(e.message ?: ""))
        }
    }
}