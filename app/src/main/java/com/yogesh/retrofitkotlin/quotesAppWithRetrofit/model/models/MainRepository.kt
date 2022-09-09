package com.yogesh.retrofitkotlin.model.models

import com.yogesh.retrofitkotlin.model.`interface`.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService){
    suspend fun getQuoteList() = retrofitService.getQuoteList()
}