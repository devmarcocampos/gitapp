package com.example.gitapp.data.usecase

import com.example.gitapp.domain.entity.Repositories
import com.example.gitapp.domain.repository.MainRepository
import com.example.gitapp.domain.usecase.UseCase

class GetRepositoriesUseCase(
    private val repository: MainRepository
) : UseCase<Int, Repositories>{
    override suspend fun execute(param: Int): Repositories {
        return repository.getRepositories(param)
    }
}