package com.github.app.data.model

import com.github.app.domain.model.SimpleUser
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiSimpleUserModel(
    @Json(name = "login")
    val name: String,
    @Json(name = "avatar_url")
    val avatarUrl: String
)

fun ApiSimpleUserModel.toDomainUser(): SimpleUser {
    return SimpleUser(name = name, avatarUrl = avatarUrl)
}