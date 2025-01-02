package com.webprog26.coroutinesplaygroundapp.users.utils

import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.Address
import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.Company
import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.Geo
import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.User
import org.json.JSONException
import org.json.JSONObject

const val ID = "id"
const val NAME = "name";
const val USER_NAME = "username"
const val EMAIL = "email"
const val ADDRESS = "address"
const val STREET = "street"
const val SUITE = "suite"
const val CITY = "city"
const val ZIPCODE = "zipcode"
const val GEO = "geo"
const val LATITUDE = "lat"
const val LONGITUDE = "lng"
const val PHONE = "phone"
const val WEBSITE = "website"
const val COMPANY = "company"
const val COMPANY_NAME = "name"
const val COMPANY_CATCH_PHRASE = "catchPhrase"
const val COMPANY_BS = "bs"


fun JSONObject.toUser(): User? {
    try {
        return User(
            getLong(ID),
            getString(NAME),
            getString(USER_NAME),
            getString(EMAIL),
            getJSONObject(ADDRESS).toAddress(),
            getString(PHONE),
            getString(WEBSITE),
            getJSONObject(COMPANY).toCompany()
        )
    } catch (e: JSONException) {
        e.printStackTrace()
    }
    return null
}

fun JSONObject.toAddress(): Address? {
    try {
        return Address(
            getString(STREET),
            getString(SUITE),
            getString(CITY),
            getString(ZIPCODE),
            getJSONObject(GEO).toGeo()

        )
    } catch (e: JSONException) {
        e.printStackTrace()
    }
    return null
}

fun JSONObject.toGeo(): Geo? {
    try {
        return Geo(
            getDouble(LATITUDE),
            getDouble(LONGITUDE)
        )
    } catch (e: JSONException) {
        e.printStackTrace()
    }
    return null
}

fun JSONObject.toCompany(): Company? {
    try {
        return Company(
            getString(COMPANY_NAME),
            getString(COMPANY_CATCH_PHRASE),
            getString(COMPANY_BS)
        )
    } catch (e: JSONException) {
        e.printStackTrace()
    }
    return null
}

fun User.toJSONObject(): JSONObject {
    val userJSONObject = JSONObject();
    userJSONObject.put(com.webprog26.coroutinesplaygroundapp.users.utils.ID, id)
    userJSONObject.put(com.webprog26.coroutinesplaygroundapp.users.utils.NAME, name)
    userJSONObject.put(com.webprog26.coroutinesplaygroundapp.users.utils.USER_NAME, username)
    userJSONObject.put(com.webprog26.coroutinesplaygroundapp.users.utils.EMAIL, email)
    userJSONObject.put(com.webprog26.coroutinesplaygroundapp.users.utils.ADDRESS, address?.toJSONObject().toString())
    userJSONObject.put(com.webprog26.coroutinesplaygroundapp.users.utils.PHONE, phone)
    userJSONObject.put(com.webprog26.coroutinesplaygroundapp.users.utils.WEBSITE, website)
    userJSONObject.put(com.webprog26.coroutinesplaygroundapp.users.utils.COMPANY, company?.toJSONObject().toString())

    return userJSONObject
}

fun Address.toJSONObject(): JSONObject {
    val addressJSONObject = JSONObject()
    addressJSONObject.put(com.webprog26.coroutinesplaygroundapp.users.utils.STREET, street)
    addressJSONObject.put(com.webprog26.coroutinesplaygroundapp.users.utils.SUITE, suite)
    addressJSONObject.put(com.webprog26.coroutinesplaygroundapp.users.utils.CITY, city)
    addressJSONObject.put(com.webprog26.coroutinesplaygroundapp.users.utils.ZIPCODE, zipCode)
    addressJSONObject.put(com.webprog26.coroutinesplaygroundapp.users.utils.GEO, geo?.toJSONObject())

    return addressJSONObject
}

fun Geo.toJSONObject(): JSONObject {
    val geoJSONObject = JSONObject()
    geoJSONObject.put(com.webprog26.coroutinesplaygroundapp.users.utils.LATITUDE, latitude)
    geoJSONObject.put(com.webprog26.coroutinesplaygroundapp.users.utils.LONGITUDE, longitude)
    return geoJSONObject
}

fun Company.toJSONObject(): JSONObject {
    val companyJSONObject = JSONObject()
    companyJSONObject.put(com.webprog26.coroutinesplaygroundapp.users.utils.COMPANY_NAME, name)
    companyJSONObject.put(com.webprog26.coroutinesplaygroundapp.users.utils.COMPANY_CATCH_PHRASE, catchPhrase)
    companyJSONObject.put(com.webprog26.coroutinesplaygroundapp.users.utils.COMPANY_BS, bs)
    return companyJSONObject
}