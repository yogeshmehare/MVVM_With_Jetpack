package com.yogesh.retrofitkotlin.dependencyInjection.model

import android.content.Context

interface DatabaseInterface {
    fun insertInDB(context: Context, user: User)
}