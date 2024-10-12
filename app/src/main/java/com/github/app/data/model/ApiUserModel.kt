package com.github.app.data.model

import com.github.app.domain.model.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiUserModel(
    @Json(name = "login")
    val name: String,
    @Json(name = "avatar_url")
    val avatarUrl: String
)

fun ApiUserModel.toDomainUser(): User {
    return User(name = name, avatarUrl = avatarUrl)
}