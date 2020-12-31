package com.news.nation.services

import com.news.nation.models.NewsResponse
import com.news.nation.utils.Constants.API_KEY
import com.news.nation.utils.Constants.categories
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("category") category: String = categories.first(),
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY,
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun getSearchQuery(
        @Query("q") searchQuery: String,
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY,
    ): Response<NewsResponse>
}