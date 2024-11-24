package com.webprog26.coroutinesplaygroundapp.data_source

import com.webprog26.coroutinesplaygroundapp.FakeLocalDataEmptyProvider
import com.webprog26.coroutinesplaygroundapp.FakeLocalDataSuccessProvider
import com.webprog26.coroutinesplaygroundapp.FakeLocalDataUpdater
import com.webprog26.coroutinesplaygroundapp.FakeNetworkDataProvider
import com.webprog26.coroutinesplaygroundapp.FakeNetworkEmptyDataProvider
import com.webprog26.coroutinesplaygroundapp.UiState
import com.webprog26.coroutinesplaygroundapp.mock.FakeErrorApi
import com.webprog26.coroutinesplaygroundapp.mock.FakeSuccessApi

import kotlinx.coroutines.test.runTest
import org.junit.Assert

import org.junit.Test
import org.mockito.InOrder
import org.mockito.Mockito
import org.mockito.kotlin.atLeast
import org.mockito.kotlin.inOrder

class DataSourceTest {

    @Test
    fun `DataSource asks data both providers (Local and Network) for data`() {

        runTest {
            val localDataProvider = Mockito.mock(DataProvider::class.java)
            Mockito.`when`(localDataProvider.getUsersList()).thenReturn(
                FakeLocalDataSuccessProvider.USERS_LIST
            )

            val networkDataProvider = Mockito.mock(DataProvider::class.java)
            Mockito.`when`(networkDataProvider.getUsersList()).thenReturn(
                FakeSuccessApi().getUsers()
            )

            val dataSource = createDataSource(localDataProvider, networkDataProvider)

            dataSource.loadUsers {
                Assert.assertEquals(UiState.Success(FakeLocalDataSuccessProvider.USERS_LIST), it)
            }

            Mockito.verify(localDataProvider).getUsersList()
            Mockito.verify(networkDataProvider).getUsersList()
        }
    }

    @Test
    fun `DataSource asks for data first and then asks for network data`() {

        runTest {
            val localDataProvider = Mockito.mock(DataProvider::class.java)
            Mockito.`when`(localDataProvider.getUsersList()).thenReturn(
                FakeLocalDataSuccessProvider.USERS_LIST
            )

            val networkDataProvider = Mockito.mock(DataProvider::class.java)
            Mockito.`when`(networkDataProvider.getUsersList()).thenReturn(
                FakeSuccessApi().getUsers()
            )

            val dataSource = createDataSource(localDataProvider, networkDataProvider)

            dataSource.loadUsers {
                Assert.assertEquals(UiState.Success(FakeLocalDataSuccessProvider.USERS_LIST), it)
            }

            val inOrder: InOrder = inOrder(localDataProvider, networkDataProvider)

            inOrder.verify(localDataProvider).getUsersList()
            inOrder.verify(networkDataProvider).getUsersList()

            inOrder.verifyNoMoreInteractions()
        }
    }



    @Test
    fun `verify that onUsersDataUpdated is called twice if both local and network data providers provide it with the data`() {
        runTest {
            val onUsersDataUpdated: (UiState) -> Unit = Mockito.mock()

            val localDataProvider = Mockito.mock(DataProvider::class.java)
            Mockito.`when`(localDataProvider.getUsersList()).thenReturn(
                FakeLocalDataSuccessProvider.USERS_LIST
            )

            val networkDataProvider = Mockito.mock(DataProvider::class.java)
            Mockito.`when`(networkDataProvider.getUsersList()).thenReturn(
                FakeSuccessApi().getUsers()
            )

            val dataSource = createDataSource(localDataProvider, networkDataProvider)

            dataSource.loadUsers(onUsersDataUpdated)

            val inOrder: InOrder = inOrder(localDataProvider, networkDataProvider)
            inOrder.verify(localDataProvider).getUsersList()
            inOrder.verify(networkDataProvider).getUsersList()

            Mockito.verify(onUsersDataUpdated, atLeast(2)).invoke(UiState.Success(FakeLocalDataSuccessProvider.USERS_LIST))

            inOrder.verifyNoMoreInteractions()
        }

    }

    @Test
    fun `verify that onUsersDataUpdated is called only once if there is no local data and network data provider provides it with the data`() {
        runTest {
            val onUsersDataUpdated: (UiState) -> Unit = Mockito.mock()

            val networkDataProvider = Mockito.mock(DataProvider::class.java)
            Mockito.`when`(networkDataProvider.getUsersList()).thenReturn(
                FakeSuccessApi().getUsers()
            )

            val dataSource = createDataSource(FakeLocalDataEmptyProvider(), networkDataProvider)

            dataSource.loadUsers(onUsersDataUpdated)

            Mockito.verify(onUsersDataUpdated, Mockito.times(1)).invoke(UiState.Success(FakeLocalDataSuccessProvider.USERS_LIST))
        }
    }

    @Test
    fun `verify that onUsersDataUpdated is never called if there is no local data and network data provider does not provide it with the data`() {
        runTest {
            val onUsersDataUpdated: (UiState) -> Unit = Mockito.mock()

            val networkDataProvider = Mockito.mock(DataProvider::class.java)
            Mockito.`when`(networkDataProvider.getUsersList()).thenReturn(
                FakeSuccessApi().getUsers()
            )

            val dataSource = createDataSource(FakeLocalDataEmptyProvider(), FakeNetworkEmptyDataProvider())

            dataSource.loadUsers(onUsersDataUpdated)

            Mockito.verifyNoInteractions(onUsersDataUpdated)
        }
    }

    @Test
    fun `verify that onUsersDataUpdated is called with an Error if there was en error during loading data from remote server`() {
        runTest {
            val onUsersDataUpdated: (UiState) -> Unit = Mockito.mock()

            val dataSource = createDataSource(FakeLocalDataEmptyProvider(), FakeNetworkDataProvider(FakeErrorApi()))

            dataSource.loadUsers(onUsersDataUpdated)

            Mockito.verify(onUsersDataUpdated).invoke(UiState.Error("HTTP 500 Response.error()"))
        }
    }

    @Test
    fun `DataSource asks DataUpdater to update local data if remote data has been loaded`() {

        val fakeLocalDataUpdater = Mockito.mock(DataUpdater::class.java)

        runTest {
            val dataSource = createDataSource(FakeLocalDataEmptyProvider(),
                FakeNetworkDataProvider(FakeSuccessApi()), fakeLocalDataUpdater)

            dataSource.loadUsers {
                Assert.assertEquals(UiState.Success(FakeLocalDataSuccessProvider.USERS_LIST), it)
            }

            Mockito.verify(fakeLocalDataUpdater).updateUsers(FakeLocalDataSuccessProvider.USERS_LIST)
        }

    }

    @Test
    fun `DataSource does not ask DataUpdater to update local data if remote data is empty`() {

        val fakeLocalDataUpdater = Mockito.mock(DataUpdater::class.java)

        runTest {
            val dataSource = createDataSource(FakeLocalDataEmptyProvider(),
                FakeNetworkEmptyDataProvider(), fakeLocalDataUpdater)

            dataSource.loadUsers {
                Assert.assertEquals(UiState.Success(FakeLocalDataSuccessProvider.USERS_LIST), it)
            }

            Mockito.verifyNoInteractions(fakeLocalDataUpdater)
        }

    }

    @Test
    fun `DataSource does not ask DataUpdater to update local data if there was an error during loading remote data`() {

        val fakeLocalDataUpdater = Mockito.mock(DataUpdater::class.java)

        runTest {
            val dataSource = createDataSource(FakeLocalDataEmptyProvider(),
                FakeNetworkDataProvider(FakeErrorApi()), fakeLocalDataUpdater)

            dataSource.loadUsers {
                Assert.assertEquals(UiState.Error("HTTP 500 Response.error()"), it)
            }

            Mockito.verifyNoInteractions(fakeLocalDataUpdater)
        }

    }

    private fun createDataSource(
        localDataProvider: DataProvider,
        networkDataProvider: DataProvider,
        localDataUpdater: DataUpdater = FakeLocalDataUpdater()
    ) : DataSource = DataSource(localDataProvider, networkDataProvider, localDataUpdater)
}