package com.example.gitapp.domain.usecase

interface UseCase<Param, Return> {
    suspend fun execute(param: Param): Return
}