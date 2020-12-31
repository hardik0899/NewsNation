package com.news.nation.repositories

import com.news.nation.db.ArticleDatabase
import com.news.nation.models.Article
import com.news.nation.models.NewsResponse
import com.news.nation.services.ApiProvider
import retrofit2.Response

class NewsRepository(private val db: ArticleDatabase) {

    // Api calls
    suspend fun getTopHeadlines(category: String, page: Int): Response<NewsResponse> {
        return ApiProvider.retrofit.getTopHeadlines(category = category, page = page)
    }

    suspend fun getSearchQuery(query: String, page: Int): Response<NewsResponse> {
        return ApiProvider.retrofit.getSearchQuery(searchQuery = query, page = page)
    }

    // Room calls
    suspend fun insertArticle(article: Article) = db.getArticleDao().insert(article)

    suspend fun deleteArticle(article: Article) = db.getArticleDao().delete(article)

    fun getSavedArticles() = db.getArticleDao().getSavedArticles()
}