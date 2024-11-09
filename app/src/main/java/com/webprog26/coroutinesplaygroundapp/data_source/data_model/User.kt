package com.webprog26.coroutinesplaygroundapp.data_source.data_model

data class User(
    val id: Long,
    val name: String,
    val userName: String,
    val email: String,
    val address: Address?,
    val phone: String,
    val website: String,
    val company: Company?
)