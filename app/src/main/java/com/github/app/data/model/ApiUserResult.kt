package com.github.app.data.model

import com.github.app.domain.model.User
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiUserResult(
    val name: String
)

fun ApiUserResult.toDomainUser(): User {
    return User()
}