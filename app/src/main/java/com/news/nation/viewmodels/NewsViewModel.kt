package com.news.nation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.news.nation.repositories.NewsRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.news.nation.NewsApplication
import com.news.nation.models.Article
import com.news.nation.models.NewsResponse
import com.news.nation.resources.NewsResource
import com.news.nation.utils.Constants.categories
import com.news.nation.utils.hasInternetConnection
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository, app: Application) : AndroidViewModel(app) {

    private val newsDataTemp = MutableLiveData<NewsResource<NewsResponse>>()
    val newsData = MutableLiveData<NewsResource<NewsResponse>>()
    private var breakingNewsPage = 1
    private var searchNewsPage = 1

    init {
        getBreakingNews()
    }

    fun getBreakingNews(category: String = categories.first()) = viewModelScope.launch {
        newsData.postValue(NewsResource.Loading())
        try {
            if (hasInternetConnection<NewsApplication>()) {
                val response = newsRepository.getTopHeadlines(category, breakingNewsPage)
                if (response.isSuccessful) {
                    newsDataTemp.postValue(NewsResource.Success(response.body()!!))
                    newsData.postValue(NewsResource.Success(response.body()!!))
                } else {
                    newsData.postValue(NewsResource.Error(response.message()))
                }
            } else {
                newsData.postValue(NewsResource.Error("No Internet Connection"))
            }
        } catch (t: Throwable) {
            newsData.postValue(NewsResource.Error(t.message!!))
        }
    }

    fun getSearchNews(searchQuery: String) = viewModelScope.launch {
        newsData.postValue(NewsResource.Loading())
        try {
            if (hasInternetConnection<NewsApplication>()) {
                val response = newsRepository.getSearchQuery(searchQuery, searchNewsPage)
                if (response.isSuccessful) {
                    newsData.postValue(NewsResource.Success(response.body()!!))
                } else {
                    newsData.postValue(NewsResource.Error(response.message()))
                }
            } else {
                newsData.postValue(NewsResource.Error("No Internet Connection"))
            }
        } catch (t: Throwable) {
            newsData.postValue(NewsResource.Error(t.message!!))
        }
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.insertArticle(article)
    }

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }

    fun getSavedArticles() = newsRepository.getSavedArticles()

    fun onSearchClose() {
        newsData.postValue(newsDataTemp.value)
    }
}