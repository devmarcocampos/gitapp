package com.example.gitapp.domain.entity

import java.io.Serializable

data class Repository(
    val id: Int,
    val name: String,
    val stargazersCount: Int,
    val forks: Int,
    val owner: Owner
) : Serializable