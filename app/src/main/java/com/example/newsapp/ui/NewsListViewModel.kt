package com.example.newsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.News
import com.example.newsapp.repository.MainRepository
import com.example.newsapp.utils.DataState
import com.example.newsapp.utils.Utility.SortBy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {

    private val newsLiveData: MutableLiveData<DataState<List<News>>> = MutableLiveData()

    init {
        getNewsFromServer()
    }

    val getNewsLiveData: LiveData<DataState<List<News>>>
        get() = newsLiveData

    private fun getNewsFromServer() {
        viewModelScope.launch {
            mainRepository.getNews().onEach { newsList ->
                newsLiveData.value = newsList
            }.launchIn(viewModelScope)
        }
    }

    fun sortNewsListBy(sortBy: SortBy) {
        (newsLiveData.value as? DataState.Success<List<News>>)?.data?.let { newsList ->
            newsLiveData.value = when (sortBy) {
                is SortBy.Popular -> {
                    DataState.Success(newsList.sortedBy { it.rank })
                }
                else -> {
                    DataState.Success(newsList.sortedByDescending { it.creationTime })
                }
            }
        }
    }
}