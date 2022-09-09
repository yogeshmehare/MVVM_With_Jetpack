package com.yogesh.retrofitkotlin.quotesAppWithRetrofit.model.models

import com.yogesh.retrofitkotlin.quotesAppWithRetrofit.model.`interface`.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService){
    suspend fun getQuoteList() = retrofitService.getQuoteList()
}