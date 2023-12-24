package com.devrachit.newsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devrachit.newsapp.models.NewsResponse
import com.devrachit.newsapp.repository.NewsRepository
import com.devrachit.newsapp.util.Resource
import kotlinx.coroutines.launch
import java.util.Locale.IsoCountryCode

class NewsViewModel(
    val newsRepository : NewsRepository
):ViewModel() {
    val breakingNews :MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val breakingNewsPage = 1

    fun getBreakingNews(countryCode: String) = viewModelScope.launch{
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode,breakingNewsPage)
    }
}