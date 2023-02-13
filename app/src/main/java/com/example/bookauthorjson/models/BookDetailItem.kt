package com.example.bookauthorjson.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookDetailItem(
    val authors: List<String>,
    val categories: List<String>,
    val isbn: String,
    val longDescription: String,
    val pageCount: Int,
    val publishedDate: PublishedDate,
    val shortDescription: String,
    val status: String,
    val thumbnailUrl: String,
    val title: String
): Parcelable