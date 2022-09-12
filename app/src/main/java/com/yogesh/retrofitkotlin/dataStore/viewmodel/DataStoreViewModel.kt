package com.yogesh.retrofitkotlin.dataStore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogesh.retrofitkotlin.dataStore.model.MyData
import com.yogesh.retrofitkotlin.dataStore.model.MyDataStore
//import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
//import javax.inject.Inject

//@HiltViewModel
class DataStoreViewModel constructor(private val myDataStore: MyDataStore) : ViewModel() {

    var keyToStore = MutableLiveData("")
    val _keyToStore : LiveData<String>
        get() = keyToStore

    var valueToStore= MutableLiveData("")
    val _valueToStore : LiveData<String>
        get() = valueToStore

    var retreivedKey= MutableLiveData("")
    val _retreivedKey : LiveData<String>
        get() = retreivedKey

    var retreivedValue = MutableLiveData("")
    val _retreivedValue : LiveData<String>
        get() = retreivedValue


    fun saveToPreferenceDataStore(){
        viewModelScope.launch {
            myDataStore.saveDataToPreferencesStore(MyData(keyToStore.value.toString(),valueToStore.value.toString()))
        }
    }

    fun getFromPreferenceDataStore(){
        viewModelScope.launch {
            myDataStore.getDataFromPreferencesStore().collect { myData ->
                retreivedKey.value = myData.key
                retreivedValue.value = myData.value
            }
        }
    }

}