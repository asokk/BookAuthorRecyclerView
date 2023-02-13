package com.example.bookauthorjson.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookauthorjson.models.BookDetail
import com.example.bookauthorjson.models.BookDetailItem
import com.example.bookauthorjson.repository.BookRepository
import com.example.bookauthorjson.utils.NetworkHelper
import com.example.bookauthorjson.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val repository: BookRepository) : ViewModel() {

    private val _books = MutableLiveData<Resource<BookDetailItem>>()
    val books: LiveData<Resource<BookDetail>>
        get() = repository.books

    init {
        fetchBookDetails()
    }

    private fun fetchBookDetails() {
        _books.postValue(Resource.loading(null))
        viewModelScope.launch {
            repository.getBookDetails()
        }
    }
}