package com.example.bookauthorjson.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bookauthorjson.databinding.FragmentBookDescriptionBinding

class BookDescriptionFragment : Fragment() {
    private var _binding: FragmentBookDescriptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.textTitle.text = arguments?.getString("title")
        binding.textAuthors.text = arguments?.getString("authors")
        binding.textPageCount.text = arguments?.getInt("pageCount").toString()
        binding.textIsbn.text = arguments?.getString("isbn")
        binding.textPublishDate.text = arguments?.getString("date")
        binding.textStatus.text = arguments?.getString("status")
        binding.textCategories.text = arguments?.getString("categories")
        binding.textDescription.text = arguments?.getString("description")


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}