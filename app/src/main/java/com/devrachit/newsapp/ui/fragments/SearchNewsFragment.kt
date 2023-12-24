package com.devrachit.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.devrachit.newsapp.R
import com.devrachit.newsapp.ui.NewsActivity
import com.devrachit.newsapp.ui.NewsViewModel

class SearchNewsFragment:Fragment(R.layout.fragment_search_news) {
    lateinit var viewModel: NewsViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as NewsActivity).viewModel
    }
}