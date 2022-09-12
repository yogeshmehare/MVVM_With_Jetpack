package com.yogesh.retrofitkotlin.dependencyInjection.model

import android.content.Context

class UserManagementService(private val localDatabaseService: LocalDatabaseService, private val firebaseService : FireBaseService) {
    fun insertUser(context: Context, user: User) {
        localDatabaseService.insertInDB(context,user)
        firebaseService.insertInDB(context,user)
    }

}