package com.example.gitapp.domain.entity

import java.io.Serializable

data class Repositories(
    val repositories: List<Repository>
) : Serializable