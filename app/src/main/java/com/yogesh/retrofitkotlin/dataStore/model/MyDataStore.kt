package com.yogesh.retrofitkotlin.dataStore.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "user"
)

class MyDataStore(val context: Context) : DataStoreInterface {


    companion object {
        val myKey = stringPreferencesKey("myKey")
        val myValue = stringPreferencesKey("myValue")
    }

    override suspend fun saveDataToPreferencesStore(data: MyData) {
        context.userPreferencesDataStore.edit { preferences ->
            preferences[myKey] = data.key
            preferences[myValue] = data.value
        }
    }

    override suspend fun getDataFromPreferencesStore(): Flow<MyData> =
        context.userPreferencesDataStore.data
            .map { preferences ->
                MyData(
                    key = preferences[myKey] ?: "",
                    value = preferences[myValue] ?: "",
                )
            }

}