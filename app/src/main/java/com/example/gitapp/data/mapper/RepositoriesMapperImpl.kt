package com.example.gitapp.data.mapper

import com.example.gitapp.data.source.remote.entity.RepositoriesResponse
import com.example.gitapp.domain.entity.Repositories
import com.example.gitapp.domain.mapper.DomainMapper

class RepositoriesMapperImpl : DomainMapper<RepositoriesResponse, Repositories> {
    override fun toDomain(from: RepositoriesResponse): Repositories {
        return Repositories(
            repositories = RepositoryMapperImpl().toDomain(from.repositories)
        )
    }

    override fun toDomain(from: List<RepositoriesResponse>): List<Repositories> {
        return from.map { toDomain(it) }
    }
}