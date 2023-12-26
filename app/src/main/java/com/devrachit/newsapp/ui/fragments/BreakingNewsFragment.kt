package com.devrachit.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devrachit.newsapp.R
import com.devrachit.newsapp.adapters.NewsAdapter
import com.devrachit.newsapp.databinding.FragmentBreakingNewsBinding
import com.devrachit.newsapp.ui.NewsActivity
import com.devrachit.newsapp.ui.viewModel.NewsViewModel
import com.devrachit.newsapp.ui.viewModel.SharedViewModel
import com.devrachit.newsapp.util.Constants.Companion.QUERY_PAGE_SIZE
import com.devrachit.newsapp.util.Resource

class BreakingNewsFragment:Fragment(R.layout.fragment_breaking_news){
    private lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: FragmentBreakingNewsBinding
    val TAG ="BreakingNewsFragment"
    private lateinit var sharedViewModel: SharedViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as NewsActivity).viewModel
        binding = FragmentBreakingNewsBinding.bind(view)
        setupRecyclerView()
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        newsAdapter.setOnItemClickListener {
            sharedViewModel.selectedArticle = it
            findNavController().navigate(
                R.id.action_breakingNewsFragment_to_articleFragment
            )
        }

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {
            response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let{newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles.toList())
                        val totalPages=newsResponse.totalResults / QUERY_PAGE_SIZE +2
                        isLastPage=viewModel.breakingNewsPage==totalPages
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
    var isLoading=false
    var isLastPage=false
    var isScrolling= false

    val scrollListener =object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount =layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem= firstVisibleItemPosition+visibleItemCount >=totalItemCount
            val isNotAtBeginning= firstVisibleItemPosition>=0
            val isTotalMoreThanVisit =totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisit && isScrolling
             if(shouldPaginate)
             {
                 viewModel.getBreakingNews("in")
                 isScrolling=false
             }


        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState==AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling= true
            }
        }
    }

    private fun setupRecyclerView()
    {
        newsAdapter= NewsAdapter()
        binding.apply{
            rvBreakingNews.adapter=newsAdapter
            rvBreakingNews.layoutManager= LinearLayoutManager(activity)
            rvBreakingNews.addOnScrollListener(this@BreakingNewsFragment.scrollListener)

        }
    }
    private fun hideProgressBar(){
        binding.paginationProgressBar.visibility=View.INVISIBLE
        isLoading=false
    }
    private fun showProgressBar()
    {
        binding.paginationProgressBar.visibility=View.VISIBLE
        isLoading=true
    }
}