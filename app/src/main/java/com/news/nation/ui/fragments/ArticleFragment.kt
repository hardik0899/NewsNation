package com.news.nation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.news.nation.R
import androidx.navigation.fragment.findNavController
import com.news.nation.models.Article
import com.news.nation.ui.ArticleActivity
import com.news.nation.utils.loadImage
import com.news.nation.viewmodels.NewsViewModel
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : Fragment(R.layout.fragment_article) {
    private lateinit var viewModel: NewsViewModel
    private lateinit var article: Article

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as ArticleActivity).viewModel
        article = (activity as ArticleActivity).article

        initArticleData(article)

        readMoreButton.setOnClickListener {
            findNavController().navigate(R.id.action_articleFragment_to_webArticleFragment)
        }
    }

    private fun initArticleData(article: Article) {
        articleImage.loadImage(article.urlToImage)
        articleTitle.text = article.title
        articleDescription.text = article.description
        articleContent.text = article.content
    }
}