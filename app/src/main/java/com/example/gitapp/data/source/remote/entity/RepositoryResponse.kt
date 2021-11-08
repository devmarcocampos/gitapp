package com.example.gitapp.data.source.remote.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepositoryResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("stargazers_count")
    var stargazersCount: Int,
    @SerializedName("forks")
    var forks: Int,
    @SerializedName("owner")
    var owner: OwnerResponse
) : Serializable