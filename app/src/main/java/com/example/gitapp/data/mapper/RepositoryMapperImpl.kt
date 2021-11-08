package com.example.gitapp.data.mapper

import com.example.gitapp.data.source.remote.entity.RepositoryResponse
import com.example.gitapp.domain.entity.Repository
import com.example.gitapp.domain.mapper.DomainMapper

class RepositoryMapperImpl : DomainMapper<RepositoryResponse, Repository> {
    override fun toDomain(from: RepositoryResponse): Repository {
        return Repository(
            id = from.id,
            name = from.name,
            stargazersCount = from.stargazersCount,
            forks = from.forks,
            owner = OwnerMapperImpl().toDomain(from.owner)
        )
    }

    override fun toDomain(from: List<RepositoryResponse>): List<Repository> {
        return from.map { toDomain(it) }
    }
}