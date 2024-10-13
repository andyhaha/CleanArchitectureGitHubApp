package com.andy.github.details.data.model

import com.andy.github.details.domain.model.Repository
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepositoryItemModel(
    val name: String?,
    val language: String?,
    @Json(name = "stargazers_count")
    val starsCount: Int?,
    val description: String?,
    @Json(name = "html_url")
    val githubUrl: String?,
    @Json(name = "forks_count")
    val forksCount: Int?,
    @Json(name = "private")
    val isPrivate: Boolean?
)

fun RepositoryItemModel.toDomainModel(): Repository {
    return Repository(
        name = name,
        language = language,
        starsCount = starsCount,
        description = description,
        githubUrl = githubUrl,
        forksCount = forksCount,
        isPrivate = isPrivate
    )
}