package com.example.bookauthorjson.utils


import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadUrl(url:String){
    Glide.with(this.context).load(url).into(this)
}

