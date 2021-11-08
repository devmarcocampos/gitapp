package com.example.gitapp.domain.repository

import com.example.gitapp.domain.entity.Repositories

interface MainRepository {
    suspend fun getRepositories(page: Int): Repositories
}