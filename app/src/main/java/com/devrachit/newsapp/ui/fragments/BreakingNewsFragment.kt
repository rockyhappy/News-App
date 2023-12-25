package com.devrachit.newsapp.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.devrachit.newsapp.R
import com.devrachit.newsapp.adapters.NewsAdapter
import com.devrachit.newsapp.databinding.FragmentBreakingNewsBinding
import com.devrachit.newsapp.models.Article
import com.devrachit.newsapp.ui.NewsActivity
import com.devrachit.newsapp.ui.NewsViewModel
import com.devrachit.newsapp.util.Resource

class BreakingNewsFragment:Fragment(R.layout.fragment_breaking_news), NewsAdapter.onItemClickListener{
    private lateinit var viewModel:NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: FragmentBreakingNewsBinding
    val TAG ="BreakingNewsFragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as NewsActivity).viewModel
        binding = FragmentBreakingNewsBinding.bind(view)
        setupRecyclerView()
        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {
            response -> 
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let{newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let{message ->
                        Log.e(TAG,"An error occured $message")
                    }
                }
                is Resource.Loading-> {
                    showProgressBar()
                }
            }
        })

    }
    private fun setupRecyclerView()
    {
        newsAdapter= NewsAdapter(this)
        binding.apply{
            rvBreakingNews.adapter=newsAdapter
            rvBreakingNews.layoutManager= LinearLayoutManager(activity)


        }
    }
    override fun onItemClick(article: Article) {
        // Handle item click here
        Toast.makeText(requireContext(), "Item clicked: ${article.title}", Toast.LENGTH_SHORT).show()
    }
    private fun hideProgressBar(){
        binding.paginationProgressBar.visibility=View.INVISIBLE
    }
    private fun showProgressBar()
    {
        binding.paginationProgressBar.visibility=View.VISIBLE
    }
}