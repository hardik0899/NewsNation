package com.news.nation.ui.fragments

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.news.nation.R
import com.news.nation.resources.NewsResource
import com.news.nation.ui.MainActivity
import com.news.nation.ui.adapters.CategoriesAdapter
import com.news.nation.ui.adapters.NewsAdapter
import com.news.nation.utils.Constants.categories
import com.news.nation.viewmodels.NewsViewModel
import kotlinx.android.synthetic.main.fragment_breaking_news.*

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        initCategoriesRecyclerView()
        initNewsRecyclerView()

        viewModel.newsData.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NewsResource.Success -> {
                    progressBarStatus(false)
                    tryAgainStatus(false)
                    newsAdapter.differ.submitList(response.data!!.articles)
                }
                is NewsResource.Error -> {
                    tryAgainStatus(true, response.message!!)
                    progressBarStatus(false)
                }
                is NewsResource.Loading -> {
                    tryAgainStatus(false)
                    progressBarStatus(true)
                }
            }
        })

        tryAgainButton.setOnClickListener { viewModel.getBreakingNews() }
    }

    private fun initCategoriesRecyclerView() {
        categoriesAdapter = CategoriesAdapter(categories)
        categoriesAdapter.onItemClickListener { viewModel.getBreakingNews(it) }

        categoriesRecyclerView.apply {
            adapter = categoriesAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun initNewsRecyclerView() {
        newsAdapter = NewsAdapter()
        breakingNewsRecyclerView.adapter = newsAdapter
    }

    private fun progressBarStatus(status: Boolean) {
        progressBar.visibility = if (status) VISIBLE else GONE
    }

    private fun tryAgainStatus(status: Boolean, message: String = "message") {
        if (status) {
            tryAgainMessage.text = message
            tryAgainLayout.visibility = View.VISIBLE
        } else {
            tryAgainLayout.visibility = View.GONE
        }
    }
}