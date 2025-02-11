package com.webprog26.coroutinesplaygroundapp.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.webprog26.coroutinesplaygroundapp.users.data_source.DataSource

class ViewModelFactory(
    private val dataSource: DataSource
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            DataSource::class.java
        )
            .newInstance(dataSource)
    }
}