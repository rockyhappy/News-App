package com.devrachit.newsapp.models

import com.devrachit.newsapp.models.Article

data class NewsResponse(
    val articles : List<Article>,
    val status : String,
    val totalResults: Int
)
