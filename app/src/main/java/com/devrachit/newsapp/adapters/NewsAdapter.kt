package com.devrachit.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devrachit.newsapp.R
import com.devrachit.newsapp.models.Article
import com.devrachit.newsapp.databinding.ItemArticlePreviewBinding


class NewsAdapter( private val itemClickListener: onItemClickListener): RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    inner class ArticleViewHolder( val binding: ItemArticlePreviewBinding):RecyclerView.ViewHolder(binding.root)

    private val differCallback = object :DiffUtil.ItemCallback<Article>(){
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticlePreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArticleViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        val binding = holder.binding

        binding.apply {
            Glide.with(root.context) // Use root.context to obtain the context from the root view
                .load(article.urlToImage)
                .into(ivArticleImage)
            tvSource.text = article.source.name
            tvTitle.text = article.tittle
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt

        }
        holder.binding.root.setOnClickListener {
            itemClickListener.onItemClick(article)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    interface onItemClickListener
    {
        fun onItemClick(article : Article)
    }
}