package com.yogesh.retrofitkotlin.dataStore.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yogesh.retrofitkotlin.dataStore.model.MyDataStore
import com.yogesh.retrofitkotlin.dataStore.viewmodel.DataStoreViewModel
import com.yogesh.retrofitkotlin.quotesAppWithRetrofit.model.models.MainRepository


@Suppress("UNCHECKED_CAST")
class DataStoreViewModelFactory(private val myDataStore: MyDataStore) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DataStoreViewModel(myDataStore) as T
    }
}