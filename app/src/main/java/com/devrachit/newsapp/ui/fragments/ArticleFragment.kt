package com.devrachit.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.devrachit.newsapp.R
import com.devrachit.newsapp.databinding.FragmentArticleBinding
import com.devrachit.newsapp.models.Article
import com.devrachit.newsapp.ui.NewsActivity
import com.devrachit.newsapp.ui.viewModel.NewsViewModel
import com.devrachit.newsapp.ui.viewModel.SharedViewModel
import com.google.android.material.snackbar.Snackbar


class ArticleFragment:Fragment(R.layout.fragment_article) {
    
    lateinit var viewModel: NewsViewModel
    private lateinit var binding :FragmentArticleBinding
    private lateinit var sharedViewModel: SharedViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as NewsActivity).viewModel
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val selectedArticle :Article? = sharedViewModel.selectedArticle
        (activity as? NewsActivity)?.findViewById<View>(R.id.bottomNavigationView)?.visibility = View.GONE
        binding=FragmentArticleBinding.bind(view)
        binding.webView.apply{
            webViewClient= WebViewClient()
            loadUrl(selectedArticle!!.url)
        }
        binding.apply{
            fab.setOnClickListener {
                viewModel.saveArticle(selectedArticle!!)
                Snackbar.make(view,"Article Saved Successfully",Snackbar.LENGTH_SHORT).show()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        (activity as? NewsActivity)?.findViewById<View>(R.id.bottomNavigationView)?.visibility = View.VISIBLE
    }
}