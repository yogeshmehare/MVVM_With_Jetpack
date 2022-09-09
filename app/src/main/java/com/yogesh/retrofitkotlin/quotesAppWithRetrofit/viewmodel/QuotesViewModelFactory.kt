package com.yogesh.retrofitkotlin.quotesAppWithRetrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yogesh.retrofitkotlin.quotesAppWithRetrofit.model.models.MainRepository


class QuotesViewModelFactory(private val mainRepository: MainRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuotesViewModel(mainRepository) as T
    }
}