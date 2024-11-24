package com.webprog26.coroutinesplaygroundapp.mock

import com.webprog26.coroutinesplaygroundapp.data_source.data_model.User
import com.webprog26.coroutinesplaygroundapp.network.UsersApi
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

class FakeErrorApi: UsersApi {

    override suspend fun getUsers(): List<User> {
        throw throw HttpException(
            Response.error<List<User>>(
                500,
                ResponseBody.create(MediaType.parse("application/json"), "")
            )
        )
    }
}