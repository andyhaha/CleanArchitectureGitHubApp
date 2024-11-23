package com.andy.github.details.data.model

import com.andy.github.details.domain.model.User
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