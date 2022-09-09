package com.yogesh.retrofitkotlin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yogesh.retrofitkotlin.model.models.MainRepository
import com.yogesh.retrofitkotlin.model.models.Quote
import kotlinx.coroutines.*



class QuotesViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    val errorMessage = MutableLiveData("")
    val quotesList = MutableLiveData<List<Quote>>()
    var job: Job? = null
    var quoteText = "Yogesh"
    var quoteIndex = 0
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData(false)

    fun getAllMovies() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getQuoteList()
            Log.d("Retrofit",response.body().toString())
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    quotesList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


    fun IncrementQuoteIndex() {
        if(quoteIndex < quotesList.value?.size?.minus(1)!!)
            quoteIndex += 1
        else
            quoteIndex = 0
    }

    fun DecrementQuoteIndex() {
        if(quoteIndex >= 1)
            quoteIndex -= 1
        else
            quoteIndex = quotesList.value?.size?.minus(1)!!
    }

}