package com.example.newsapp.utils

interface EntityMapper<NetworkEntity, DomainModel> {

    fun mapFromEntity(entity: NetworkEntity): DomainModel

    fun mapToEntity(domainModel: DomainModel): NetworkEntity
}