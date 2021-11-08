package com.example.gitapp.data.repository

import com.example.gitapp.data.mapper.RepositoriesMapperImpl
import com.example.gitapp.data.source.remote.Api
import com.example.gitapp.domain.entity.Repositories
import com.example.gitapp.domain.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepositoryImpl(
    private val api: Api,
    private val mapper: RepositoriesMapperImpl
) : MainRepository {

    override suspend fun getRepositories(page: Int): Repositories =
        withContext(Dispatchers.IO) {
            api.getRepositories(LANGUAGE_KOTLIN, STARS, page.toString()).let {
                mapper.toDomain(it)
            }
        }

    companion object {
        const val LANGUAGE_KOTLIN = "language:kotlin"
        const val STARS = "stars"
    }
}