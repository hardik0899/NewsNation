package com.news.nation.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.news.nation.models.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article): Long

    @Query("SELECT * FROM article_table")
    fun getSavedArticles(): LiveData<List<Article>>

    @Delete
    suspend fun delete(article: Article)
}