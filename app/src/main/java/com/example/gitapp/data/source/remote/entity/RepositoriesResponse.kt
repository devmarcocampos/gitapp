package com.example.gitapp.data.source.remote.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepositoriesResponse (
    @SerializedName("items")
    var repositories: List<RepositoryResponse>
) : Serializable