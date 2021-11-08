package com.example.gitapp.domain.entity

import java.io.Serializable

data class Owner(
    val id: Int,
    val login: String,
    val avatar_url: String
) : Serializable