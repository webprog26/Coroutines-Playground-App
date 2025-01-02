package com.webprog26.coroutinesplaygroundapp.users.data_source.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(RoomUserTypeConverters::class)
abstract class UsersDatabase: RoomDatabase() {

    abstract fun usersDao(): UsersDao

    companion object {

        private const val DATABASE_NAME = "users.db"

        private var INSTANCE: UsersDatabase? = null

        fun getInstance(context: Context): UsersDatabase {
            if (INSTANCE == null) {
                synchronized(UsersDatabase::class) {
                    INSTANCE = buildRoomDb(context)
                }
            }

            return INSTANCE!!
        }

        private fun buildRoomDb(context: Context): UsersDatabase {
            return Room.databaseBuilder(context.applicationContext,
                UsersDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}