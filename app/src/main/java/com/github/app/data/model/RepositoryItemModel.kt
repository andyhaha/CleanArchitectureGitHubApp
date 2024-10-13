package com.github.app.data.model

import com.github.app.domain.model.Repository
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
    val forksCount: Int?
)

fun RepositoryItemModel.toDomainModel(): Repository {
    return Repository(
        name = name,
        language = language,
        starsCount = starsCount,
        description = description,
        githubUrl = githubUrl,
        forksCount = forksCount
    )
}