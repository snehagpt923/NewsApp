package com.example.newsapp.repository

import com.example.newsapp.domain.News
import com.example.newsapp.network.NetworkMapper
import com.example.newsapp.network.NewsApiService
import com.example.newsapp.utils.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository constructor(
    private val newsApiService: NewsApiService,
    private val networkMapper: NetworkMapper
) {
    suspend fun getNews(): Flow<DataState<List<News>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkNews = newsApiService.getNews()
            val newsList = networkMapper.mapFromEntityList(networkNews)
            emit(DataState.Success(newsList))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}