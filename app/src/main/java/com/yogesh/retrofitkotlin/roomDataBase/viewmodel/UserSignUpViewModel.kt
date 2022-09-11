package com.yogesh.retrofitkotlin.roomDataBase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogesh.retrofitkotlin.roomDataBase.model.UserDatabase
import com.yogesh.retrofitkotlin.roomDataBase.model.UserEntity
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class UserSignUpViewModel : ViewModel() {

    var userName = MutableLiveData("")
    var userEmail = MutableLiveData("")
    var userPassword = MutableLiveData("")
    var userBirthDate = MutableLiveData("")
    var userList = MutableLiveData<List<UserEntity>>(ArrayList())
    lateinit var userDatabase: UserDatabase

    fun saveUserToRoomDb() {
        viewModelScope.launch {
            userDatabase.userDao().insertUser(
                UserEntity(
                    0,
                    userName.value.toString(),
                    userEmail.value.toString(),
                    userPassword.value.toString(),
                    userBirthDate.value.toString()
                )
            )
        }
        getUsersFromRoomDb()
    }

    private fun getUsersFromRoomDb() {
        userList.postValue(userDatabase.userDao().getUsers().value)
    }

}