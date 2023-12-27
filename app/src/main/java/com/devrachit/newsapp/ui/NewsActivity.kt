package com.devrachit.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.devrachit.newsapp.R
import androidx.navigation.ui.setupWithNavController
import com.devrachit.newsapp.databinding.ActivityNewsBinding
import com.devrachit.newsapp.db.ArticleDatabase
import com.devrachit.newsapp.repository.NewsRepository
import com.devrachit.newsapp.ui.viewModel.NewsViewModel
import com.devrachit.newsapp.ui.viewModel.NewsViewModelProviderFactory

class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
     lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {

            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            bottomNavigationView.setupWithNavController(navController)
        }
        val newsRepository =NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application,newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)


    }
}
