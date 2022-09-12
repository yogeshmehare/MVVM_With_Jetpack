package com.yogesh.retrofitkotlin.listAdapter.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yogesh.retrofitkotlin.listAdapter.model.`interface`.RetrofitQuotesService

class QuotesRepository(private val retrofitQuotesService: RetrofitQuotesService) {

    private val quotesLiveData = MutableLiveData<Quotes>()
    val mQuotesLiveData:LiveData<Quotes>
        get() = quotesLiveData

    suspend fun getQuotes(pageNo:Int) {
        val result = retrofitQuotesService.getQuotes(pageNo)
        if(result.body() !=null){
            quotesLiveData.postValue(result.body())
        }
    }

}