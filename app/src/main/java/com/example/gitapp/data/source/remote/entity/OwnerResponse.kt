package com.example.gitapp.data.source.remote.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OwnerResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("login")
    var login: String,
    @SerializedName("avatar_url")
    var avatar_url: String
) : Serializable