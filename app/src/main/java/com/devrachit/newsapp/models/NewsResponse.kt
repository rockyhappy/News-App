package com.devrachit.newsapp.models

import com.devrachit.newsapp.models.Article

data class NewsResponse(
    val articles : MutableList<Article>,
    val status : String,
    val totalResults: Int
)
