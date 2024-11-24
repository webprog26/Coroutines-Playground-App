package com.webprog26.coroutinesplaygroundapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.webprog26.coroutinesplaygroundapp.data_source.DataSource
import com.webprog26.coroutinesplaygroundapp.mock.FakeErrorApi
import com.webprog26.coroutinesplaygroundapp.mock.FakeSuccessApi
import com.webprog26.coroutinesplaygroundapp.utils.ReplaceMainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@OptIn(ExperimentalCoroutinesApi::class)
class UsersViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get: Rule
    val replaceMainDispatcherRule = ReplaceMainDispatcherRule()

    private val receivedUiStates = mutableListOf<UiState>()

    @Test
    fun `should return users list if local data exists`() {
        // Arrange
        val dataSource = DataSource(FakeLocalDataSuccessProvider(), FakeNetworkEmptyDataProvider(), FakeLocalDataUpdater())
        val viewModel = UsersViewModel(dataSource)
        observeViewModel(viewModel)
        // Act
        viewModel.loadUsers()
        //Assert
        Assert.assertEquals(listOf(
            UiState.Success(FakeLocalDataSuccessProvider.USERS_LIST)
        ), receivedUiStates)
    }

    private fun observeViewModel(viewModel: UsersViewModel) {
        viewModel.usersListData.observeForever { uiState ->
            receivedUiStates.add(uiState)
        }
    }

    @Test
    fun `should return an empty list if local data does not exist`() {
        // Arrange
        val dataSource = DataSource(FakeLocalDataEmptyProvider(), FakeNetworkEmptyDataProvider(), FakeLocalDataUpdater())
        val viewModel = UsersViewModel(dataSource)
        observeViewModel(viewModel)
        // Act
        viewModel.loadUsers()
        //Assert
        Assert.assertTrue(receivedUiStates.isEmpty())
    }

    @Test
    fun `should load users from network if local data does not exist yet`() = runTest {
        val dataSource = DataSource(FakeLocalDataEmptyProvider(), FakeNetworkDataProvider(FakeSuccessApi()), FakeLocalDataUpdater())
        val viewModel = UsersViewModel(dataSource)
        observeViewModel(viewModel)
        // Act
        viewModel.loadUsers()
        advanceUntilIdle()

        // Assert
        Assert.assertEquals(listOf(
            UiState.Success(FakeLocalDataSuccessProvider.USERS_LIST)
        ), receivedUiStates)
    }

    @Test
    fun `should return error when server is not available` () {
        runTest {
            val dataSource = DataSource(FakeLocalDataEmptyProvider(), FakeNetworkDataProvider(FakeErrorApi()), FakeLocalDataUpdater())
            val viewModel = UsersViewModel(dataSource)
            observeViewModel(viewModel)

            viewModel.loadUsers()
            advanceUntilIdle()

            Assert.assertEquals(listOf(UiState.Error("HTTP 500 Response.error()")), receivedUiStates)
        }
    }
}