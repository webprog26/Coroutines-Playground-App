package com.webprog26.coroutinesplaygroundapp.users.network

import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://jsonplaceholder.typicode.com"
interface UsersApi {

    @GET("/users")
    suspend fun getUsers(): List<User>
}

fun createUsersApi(): UsersApi {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(UsersApi::class.java)
}