package com.example.newsapp.network

import com.example.newsapp.domain.News
import com.example.newsapp.utils.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject constructor() : EntityMapper<NewsNetworkEntity, News> {
    override fun mapFromEntity(entity: NewsNetworkEntity): News {
        return News(
            id = entity.id,
            title = entity.title,
            description = entity.description,
            bannerUrl = entity.bannerUrl,
            creationTime = entity.timeCreated,
            rank = entity.rank
        )
    }

    override fun mapToEntity(domainModel: News): NewsNetworkEntity {
        return NewsNetworkEntity(
            id = domainModel.id,
            title = domainModel.title,
            description = domainModel.description,
            bannerUrl = domainModel.bannerUrl,
            timeCreated = domainModel.creationTime,
            rank = domainModel.rank
        )
    }

    fun mapFromEntityList(entities: List<NewsNetworkEntity>): List<News> {
        return entities.map { mapFromEntity(it) }
    }
}