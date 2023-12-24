package com.devrachit.newsapp.repository

import com.devrachit.newsapp.api.RetrofitInstance
import com.devrachit.newsapp.db.ArticleDatabase

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode :String, pageNumber:Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)
}