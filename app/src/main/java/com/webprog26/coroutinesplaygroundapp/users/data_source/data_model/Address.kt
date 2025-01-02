package com.webprog26.coroutinesplaygroundapp.users.data_source.data_model

data class Address (
    val street: String,
    val suite: String,
    val city: String,
    val zipCode: String,
    val geo: Geo?
)