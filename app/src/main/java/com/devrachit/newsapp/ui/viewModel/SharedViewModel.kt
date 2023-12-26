package com.devrachit.newsapp.ui.viewModel

import androidx.lifecycle.ViewModel
import com.devrachit.newsapp.models.Article

class SharedViewModel : ViewModel() {
    var selectedArticle: Article? = null
}
