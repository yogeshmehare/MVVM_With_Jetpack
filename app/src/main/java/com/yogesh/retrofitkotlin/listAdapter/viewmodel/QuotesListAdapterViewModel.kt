package com.yogesh.retrofitkotlin.listAdapter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogesh.retrofitkotlin.listAdapter.model.Quotes
import com.yogesh.retrofitkotlin.listAdapter.model.QuotesRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class QuotesListAdapterViewModel constructor(private val quotesRepository: QuotesRepository) :
    ViewModel() {

    private val errorMessage = MutableLiveData("")
    val quotes : LiveData<Quotes>
        get() = quotesRepository.mQuotesLiveData
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData(false)

    init {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            quotesRepository.getQuotes(1)
        }
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
        loading.postValue(false)
    }
}