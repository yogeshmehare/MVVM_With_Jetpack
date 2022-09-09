package com.yogesh.retrofitkotlin.quotesAppWithRetrofit.model.`interface`

import com.yogesh.retrofitkotlin.quotesAppWithRetrofit.model.models.Quote
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("quotes")
    suspend fun getQuoteList() : Response<List<Quote>>

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://zenquotes.io/api/")
//                    .baseUrl("http://api.quotable.io/random")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}