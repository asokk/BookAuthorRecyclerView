package com.example.bookauthorjson.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookauthorjson.api.BookDetailService
import com.example.bookauthorjson.models.BookDetail
import com.example.bookauthorjson.utils.Resource

class BookRepository(private val bookDetailService: BookDetailService) {

    private val bookLiveData = MutableLiveData<Resource<BookDetail>>()

    val books: LiveData<Resource<BookDetail>>
    get() = bookLiveData

    suspend fun getBookDetails() {
        val result = bookDetailService.getBookDetail()
        if (result.body() != null ) {
            bookLiveData.postValue(Resource.success(result.body()))
        } else {
            bookLiveData.postValue(Resource.error("No internet connnection", null))
        }
    }
}