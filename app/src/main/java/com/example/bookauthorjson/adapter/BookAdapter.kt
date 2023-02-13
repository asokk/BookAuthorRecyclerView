package com.example.bookauthorjson.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookauthorjson.databinding.ItemBookRowBinding
import com.example.bookauthorjson.models.BookDetailItem

class BookAdapter(
    private val context: Context,
    private val bookItemDetailList: ArrayList<BookDetailItem>,
    private val callBack: OnItemClick
) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemBookRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            holder.bind(bookItemDetailList[position])
            holder.itemView.setOnClickListener {
                callBack.onViewClick(bookItemDetailList[position])
            }
        }catch (e: java.lang.Exception) {
          e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return bookItemDetailList.size
    }

    inner class ViewHolder(private val binding: ItemBookRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bookDetailItem: BookDetailItem) {
            binding.bookTitle.text = bookDetailItem.title
            binding.bookAuthor.text = bookDetailItem.authors.toString()
            Glide.with(context).asBitmap().load(bookDetailItem.thumbnailUrl).into(binding.bookImage)
        }
    }

    interface OnItemClick{
        fun  onViewClick(bookDetailItem: BookDetailItem)
    }
}