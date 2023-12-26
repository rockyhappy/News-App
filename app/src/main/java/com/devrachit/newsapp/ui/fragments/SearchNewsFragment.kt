package com.devrachit.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.devrachit.newsapp.R
import com.devrachit.newsapp.adapters.NewsAdapter
import com.devrachit.newsapp.databinding.FragmentBreakingNewsBinding
import com.devrachit.newsapp.databinding.FragmentSearchNewsBinding
import com.devrachit.newsapp.models.Article
import com.devrachit.newsapp.ui.NewsActivity
import com.devrachit.newsapp.ui.NewsViewModel
import com.devrachit.newsapp.ui.SharedViewModel
import com.devrachit.newsapp.util.Constants.Companion.SEARCH_NEWS_TIME_DELAY
import com.devrachit.newsapp.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchNewsFragment:Fragment(R.layout.fragment_search_news){
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: FragmentSearchNewsBinding
    var TAG = "SearchNewsFragment"
    private lateinit var sharedViewModel: SharedViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as NewsActivity).viewModel
        binding = FragmentSearchNewsBinding.bind(view)
        setupRecyclerView()
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        newsAdapter.setOnItemClickListener {article ->
            sharedViewModel.selectedArticle=article
            findNavController().navigate(
                R.id.action_searchNewsFragment_to_articleFragment
            )
        }
        var job: Job?=null
        binding.etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job= MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable?.let{
                    if(editable.toString().isNotEmpty()){
                        viewModel.searchNews(editable.toString())
                    }
                }
            }

        }



        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response->
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
                else -> {
                    hideProgressBar()
                    Log.e(TAG,"Something Wrong Happened")
                }
            }
        })
    }
    private fun setupRecyclerView()
    {
        newsAdapter= NewsAdapter()
        binding.apply{
            rvSearchNews.adapter=newsAdapter
            rvSearchNews.layoutManager= LinearLayoutManager(activity)


        }
    }
//    override fun onItemClick(article: Article) {
//        // Handle item click here
//        Toast.makeText(requireContext(), "Item clicked: ${article.title}", Toast.LENGTH_SHORT).show()
//    }
    private fun hideProgressBar(){
        binding.paginationProgressBar.visibility=View.INVISIBLE
    }
    private fun showProgressBar()
    {
        binding.paginationProgressBar.visibility=View.VISIBLE
    }
}