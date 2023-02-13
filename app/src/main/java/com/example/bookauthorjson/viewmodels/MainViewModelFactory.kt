package com.example.bookauthorjson.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookauthorjson.repository.BookRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val repository: BookRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}