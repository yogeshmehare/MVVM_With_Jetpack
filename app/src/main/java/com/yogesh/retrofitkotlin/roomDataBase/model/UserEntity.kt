package com.yogesh.retrofitkotlin.roomDataBase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "userTable")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var userId: Int,
    @ColumnInfo(name = "userName")
    val userName: String,
    @ColumnInfo(name = "userEmail")
    val userEmail: String,
    @ColumnInfo(name = "userPassword")
    val userPassword: String,
    @ColumnInfo(name = "userBirthDate")
    val userBirthDate: String
)