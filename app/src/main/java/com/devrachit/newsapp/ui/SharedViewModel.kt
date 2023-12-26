package com.devrachit.newsapp.ui

import androidx.lifecycle.ViewModel
import com.devrachit.newsapp.models.Article

class SharedViewModel : ViewModel() {
    var selectedArticle: Article? = null
}
