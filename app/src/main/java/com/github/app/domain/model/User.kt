package com.github.app.domain.model

import com.github.app.common.formatToK

data class User(
    val name: String?,
    val avatarUrl: String?,
    val fullName: String?,
    val followers: Int?,
    val following: Int?
)

fun User.formatFullName(): String {
    return fullName ?: "Full Name: N/A"
}

fun User.formatName(): String {
    return name ?: "Name: N/A"
}

fun User.formatFollowers(): String {
    val count = followers ?: 0
    return count.formatToK()
}

fun User.formatFollowing(): String {
    val count = following ?: 0
    return count.formatToK()
}
