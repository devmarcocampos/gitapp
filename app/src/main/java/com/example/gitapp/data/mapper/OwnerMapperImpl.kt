package com.example.gitapp.data.mapper

import com.example.gitapp.data.source.remote.entity.OwnerResponse
import com.example.gitapp.domain.entity.Owner
import com.example.gitapp.domain.mapper.DomainMapper

class OwnerMapperImpl : DomainMapper<OwnerResponse, Owner> {
    override fun toDomain(from: OwnerResponse): Owner {
        return Owner(
            id = from.id,
            login = from.login,
            avatar_url = from.avatar_url
        )
    }

    override fun toDomain(from: List<OwnerResponse>): List<Owner> {
        return from.map { toDomain(it) }
    }
}