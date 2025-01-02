package com.webprog26.coroutinesplaygroundapp.users.data_source.data_model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val address: Address?,
    val phone: String,
    val website: String,
    val company: Company?
)