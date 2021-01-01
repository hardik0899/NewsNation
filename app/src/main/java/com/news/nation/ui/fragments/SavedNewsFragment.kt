package com.news.nation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.news.nation.R
import com.news.nation.ui.MainActivity
import com.news.nation.ui.adapters.NewsAdapter
import com.news.nation.viewmodels.NewsViewModel
import kotlinx.android.synthetic.main.fragment_saved_news.*

class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        initNewsRecyclerView()

        viewModel.getSavedArticles().observe(viewLifecycleOwner, {
            newsAdapter.differ.submitList(it)
        })
    }

    private fun initNewsRecyclerView() {
        newsAdapter = NewsAdapter()
        savedArticlesRecyclerView.adapter = newsAdapter
    }
}