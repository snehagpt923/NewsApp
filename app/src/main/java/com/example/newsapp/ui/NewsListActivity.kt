package com.example.newsapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.R
import com.example.newsapp.domain.News
import com.example.newsapp.utils.DataState
import com.example.newsapp.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_news.*


@AndroidEntryPoint
class NewsListActivity : AppCompatActivity(), NewsListRecyclerViewAdapter.ItemClickListener {

    private val viewModel: NewsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.getNewsLiveData.observe(this, {
            when (it) {
                is DataState.Success<List<News>> -> {
                    displayProgressBar(false)
                    initViews(it.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(it.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?) {
        if (message != null) {
            tv_error.visibility = View.GONE
            tv_error.text = message
        } else {
            tv_error.text = ""
            tv_error.visibility = View.GONE
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun initViews(newsList: List<News>) {
        val adapter = NewsListRecyclerViewAdapter(this, newsList)
        adapter.setClickListener(this)
        rv_news.adapter = adapter
    }

    override fun onItemClick(view: View?, news: News) {
        this.showToast(news.title)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.recent -> {
                true
            }
            R.id.popular -> {
                true
            }
            else -> super.onOptionsItemSelected(item);
        }
    }
}