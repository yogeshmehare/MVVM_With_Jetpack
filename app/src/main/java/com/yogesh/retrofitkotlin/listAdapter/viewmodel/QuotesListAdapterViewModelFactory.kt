package com.yogesh.retrofitkotlin.listAdapter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yogesh.retrofitkotlin.listAdapter.model.QuotesRepository


@Suppress("UNCHECKED_CAST")
class QuotesListAdapterViewModelFactory(private val quotesRepository: QuotesRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuotesListAdapterViewModel(quotesRepository) as T
    }
}