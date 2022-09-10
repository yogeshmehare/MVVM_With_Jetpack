package com.yogesh.retrofitkotlin.dataStore.model

import kotlinx.coroutines.flow.Flow

interface DataStoreInterface {
    suspend fun saveDataToPreferencesStore(data: MyData)
    suspend fun getDataFromPreferencesStore():Flow<MyData>
}
