package com.github.app.domain.model

import com.github.app.common.formatToK

data class Repository(
    val name: String?,
    val language: String?,
    val starsCount: Int?,
    val description: String?,
    val githubUrl: String?,
    val forksCount: Int?,
    val isPrivate: Boolean?
) {

    fun formattedName(): String {
        val result = if (name.isNullOrBlank()) {
            "No description provided"
        } else {
            name
        }
        return result
    }

    fun formattedStarsCount(): String {
        val count = starsCount ?: 0
        return count.formatToK()
    }

    fun formattedForksCount(): String {
        val count = forksCount ?: 0
        return count.formatToK()
    }

    fun formattedDescription(): String {
        val result = if (description.isNullOrBlank()) {
            "No description provided"
        } else {
            description
        }
        return result
    }

    fun formattedRepoType(): String {
        val result = if (isPrivate?.not() == true) {
            "Public"
        } else {
            "Private"
        }
        return result
    }
}
