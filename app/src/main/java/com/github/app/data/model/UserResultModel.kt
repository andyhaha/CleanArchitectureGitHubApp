package com.github.app.data.model

import com.github.app.domain.model.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiUserResult(
    @Json(name = "login")
    val name: String?,
    @Json(name = "avatar_url")
    val avatarUrl: String?,
    @Json(name = "name")
    val fullName: String?,
    val followers: Int?,
    val following: Int?
)

fun ApiUserResult.toDomainUser(): User {
    return User(
        name = name,
        avatarUrl = avatarUrl,
        fullName = fullName,
        followers = followers,
        following = following
    )
}