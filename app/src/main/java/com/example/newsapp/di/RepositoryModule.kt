package com.example.newsapp.di

import com.example.newsapp.network.NetworkMapper
import com.example.newsapp.network.NewsApiService
import com.example.newsapp.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        newsApiService: NewsApiService,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(newsApiService, networkMapper)
    }
}