package com.yogesh.retrofitkotlin.listAdapter.model.`interface`

import com.yogesh.retrofitkotlin.listAdapter.model.Quotes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitQuotesService {

    @GET("quotes")
    suspend fun getQuotes(@Query("page") page:Int):Response<Quotes>

}