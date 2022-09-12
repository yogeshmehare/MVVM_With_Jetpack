package com.yogesh.retrofitkotlin.dependencyInjection.model

import android.content.Context
import android.widget.Toast

class LocalDatabaseService : DatabaseInterface {
    override fun insertInDB(context:Context, user: User){
        Toast.makeText(context, "Hi user with username ${user.name} inserted in local database", Toast.LENGTH_SHORT).show()
    }
}
