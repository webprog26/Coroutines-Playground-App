package com.webprog26.coroutinesplaygroundapp.users.data_source.database

import androidx.room.TypeConverter
import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.Address
import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.Company
import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.Geo
import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.User
import com.webprog26.coroutinesplaygroundapp.users.utils.toAddress
import com.webprog26.coroutinesplaygroundapp.users.utils.toCompany
import com.webprog26.coroutinesplaygroundapp.users.utils.toGeo
import com.webprog26.coroutinesplaygroundapp.users.utils.toJSONObject
import com.webprog26.coroutinesplaygroundapp.users.utils.toUser
import org.json.JSONObject

class RoomUserTypeConverters {

    @TypeConverter
    fun userFromJsonString(jsonString: String?) =
        if (jsonString != null) {
            JSONObject(jsonString).toUser()
        } else {
            null
        }

    @TypeConverter
    fun userToJSONString(user: User?) = user?.toJSONObject().toString()

    @TypeConverter
    fun addressFromJSONString(jsonString: String?) =
        if (jsonString != null) {
            JSONObject(jsonString).toAddress()
        } else {
            null
        }

    @TypeConverter
    fun addressToJSONString(address: Address?) = address?.toJSONObject()?.toString()

    @TypeConverter
    fun geoFromJSONString(jsonString: String?) =
        if (jsonString != null) {
            JSONObject(jsonString).toGeo()
        } else {
            null
        }

    @TypeConverter
    fun addressToJSONString(geo: Geo?) = geo?.toJSONObject()?.toString()

    @TypeConverter
    fun companyFromJSONString(jsonString: String?) =
        if (jsonString != null) {
            JSONObject(jsonString).toCompany()
        } else {
            null
        }

    @TypeConverter
    fun companyToJSONString(company: Company?) = company?.toJSONObject()?.toString()
}