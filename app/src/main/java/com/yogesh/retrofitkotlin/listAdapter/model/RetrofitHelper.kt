package com.yogesh.retrofitkotlin.listAdapter.model

import com.yogesh.retrofitkotlin.listAdapter.model.`interface`.RetrofitQuotesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    var retrofitService: RetrofitQuotesService? = null
    const val baseUrl = "http://api.quotable.io/"

    fun getInstance(): RetrofitQuotesService {
        if (retrofitService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofitService = retrofit.create(RetrofitQuotesService::class.java)
        }
        return retrofitService!!
    }


}