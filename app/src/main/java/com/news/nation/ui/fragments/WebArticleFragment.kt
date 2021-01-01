package com.news.nation.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.news.nation.R
import com.news.nation.models.Article
import com.news.nation.ui.ArticleActivity
import kotlinx.android.synthetic.main.fragment_web_article.*

class WebArticleFragment : Fragment(R.layout.fragment_web_article) {

    private lateinit var article: Article

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        article = (activity as ArticleActivity).article

        articleWebView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
    }
}