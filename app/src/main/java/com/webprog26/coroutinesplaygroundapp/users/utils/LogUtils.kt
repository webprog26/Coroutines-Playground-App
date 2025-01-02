package com.webprog26.coroutinesplaygroundapp.users.utils

import android.util.Log

const val TAG = "coroutines_deb";
fun logDebug(message: String, tag: String = TAG) {
    log(tag, message, Log.DEBUG)
}

fun logError(message: String, tag: String = TAG) {
    log(tag, message, Log.ERROR)
}

fun logVerbose(message: String, tag: String = TAG) {
    log(tag, message, Log.VERBOSE)
}

private fun log(tag: String, message: String, logLevel: Int) {
    when(logLevel) {
        Log.DEBUG -> Log.d(tag, message)
        Log.ERROR -> Log.e(tag, message)
        Log.VERBOSE -> Log.v(tag, message)
    }

}