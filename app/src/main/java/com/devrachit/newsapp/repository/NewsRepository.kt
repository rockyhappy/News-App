package com.devrachit.newsapp.repository

import com.devrachit.newsapp.api.RetrofitInstance
import com.devrachit.newsapp.db.ArticleDatabase
import com.devrachit.newsapp.models.Article

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode :String, pageNumber:Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)
    suspend fun searchNews(searchQuery : String , pageNumber: Int ) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article)= db.getArticleDao().upsert(article)

    fun getSavedNews() =db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article)=db.getArticleDao().deleteArticle(article)
}