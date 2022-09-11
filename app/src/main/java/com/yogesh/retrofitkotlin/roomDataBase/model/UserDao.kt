package com.yogesh.retrofitkotlin.roomDataBase.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Transaction


@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(userEntity: UserEntity)

    @Query("Select * from userTable")
    fun getUsers() : LiveData<List<UserEntity>>

    @Query("Select * from userTable WHERE userEmail LIKE :userEmail")
    fun getUser(userEmail:String) : LiveData<List<UserEntity>>

    @Query("UPDATE userTable SET userName=:name WHERE userId LIKE :id")
    suspend fun updateUserName(id:Int,name:String)

    @Update
    suspend fun updateUser(user:UserEntity)

    @Delete
    suspend fun deleteUser(user:UserEntity)

    @Transaction
    suspend fun insertAndDeleteInTransaction(newUser: UserEntity?, oldUser: UserEntity?) {
        // Anything inside this method runs in a single transaction.
        deleteUser(oldUser!!)
        insertUser(newUser!!)
    }
}