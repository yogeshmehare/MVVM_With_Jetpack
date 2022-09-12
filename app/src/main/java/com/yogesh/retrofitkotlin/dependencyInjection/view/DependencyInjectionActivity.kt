package com.yogesh.retrofitkotlin.dependencyInjection.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yogesh.retrofitkotlin.R
import com.yogesh.retrofitkotlin.dependencyInjection.model.*

class DependencyInjectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dependency_injection)

        val databaseService = LocalDatabaseService()
        val firebaseService = FireBaseService()
        val userManagementService = UserManagementService(databaseService,firebaseService)
        val user = User("yogesh",1501)
        userManagementService.insertUser(this.applicationContext, user)
    }
}