package com.example.bookauthorjson.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookauthorjson.R
import com.example.bookauthorjson.adapter.BookAdapter
import com.example.bookauthorjson.api.BookDetailService
import com.example.bookauthorjson.api.RetrofitHelper
import com.example.bookauthorjson.databinding.FragmentBookBinding
import com.example.bookauthorjson.models.BookDetailItem
import com.example.bookauthorjson.repository.BookRepository
import com.example.bookauthorjson.utils.Status
import com.example.bookauthorjson.viewmodels.MainViewModel
import com.example.bookauthorjson.viewmodels.MainViewModelFactory
import java.text.SimpleDateFormat

class BookFragment : Fragment() {

    private var _binding: FragmentBookBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel
    private lateinit var bookAdapter: BookAdapter

    private var bookDetailItemList = arrayListOf<BookDetailItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookService = RetrofitHelper.getInstance().create(BookDetailService::class.java)
        val repository = BookRepository(bookService)

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.books.observe(requireActivity()) {
            when(it.status) {
                Status.SUCCESS -> {
                    it.data?.forEach {
                        bookDetailItemList.add(it)
                    }
                    binding.bookList.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }
                Status.LOADING -> {
                  binding.bookList.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    binding.progressBar.visibility = View.GONE
                }
            }

            bookAdapter = BookAdapter(
                requireContext(),
                bookDetailItemList,
                clickView
            )
            binding.bookList.adapter = bookAdapter
        }
    }

    private val clickView = object : BookAdapter.OnItemClick {
        override fun onViewClick(bookDetailItem: BookDetailItem) {
            val bundle = Bundle()
            bundle.putString("title", bookDetailItem.title)
            bundle.putString("authors", bookDetailItem.authors.toString())
            bundle.putInt("pageCount", bookDetailItem.pageCount)
            bundle.putString("isbn", bookDetailItem.isbn)
            bundle.putString("date", modifiedDate(bookDetailItem.publishedDate.`$date`))
            bundle.putString("status", bookDetailItem.status)
            bundle.putString("categories", bookDetailItem.categories.toString())
            bundle.putString("description", bookDetailItem.longDescription)
            findNavController().navigate(
                R.id.action_bookFragment_to_bookDescriptionFragment,
                bundle
            )
        }
    }

    private fun modifiedDate(date: String): String? {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val outputFormat = SimpleDateFormat("yyyy/MM/dd")
        return outputFormat.format(inputFormat.parse(date))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}