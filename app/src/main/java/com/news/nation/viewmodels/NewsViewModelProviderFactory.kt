package com.news.nation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.news.nation.repositories.NewsRepository


@Suppress("UNCHECKED_CAST")
class NewsViewModelProviderFactory (
    private val newsRepository: NewsRepository,
    private val app: Application,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository, app) as T
    }
}