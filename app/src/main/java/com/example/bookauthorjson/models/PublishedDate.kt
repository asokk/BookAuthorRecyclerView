package com.example.bookauthorjson.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PublishedDate(
    val `$date`: String
): Parcelable