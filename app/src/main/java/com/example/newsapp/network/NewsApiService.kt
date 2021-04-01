package com.example.newsapp.network

import retrofit2.http.GET

interface NewsApiService {

    @GET("carousell_news.json")
    suspend fun getNews(): List<NewsNetworkEntity>
}