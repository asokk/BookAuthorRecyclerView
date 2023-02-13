package com.example.bookauthorjson.api

import com.example.bookauthorjson.models.BookDetail
import retrofit2.Response
import retrofit2.http.GET

interface BookDetailService {
    @GET("https://raw.githubusercontent.com/bvaughn/infinite-list-reflow-examples/master/books.json")
    suspend fun getBookDetail() : Response<BookDetail>
}