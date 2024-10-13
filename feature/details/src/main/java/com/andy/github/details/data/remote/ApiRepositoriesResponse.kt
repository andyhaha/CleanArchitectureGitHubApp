package com.andy.github.details.data.remote

import com.andy.github.details.data.model.RepositoryItemModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiRepositoriesResponse(
    @Json(name = "total_count")
    val totalCount: Int,
    val items: List<RepositoryItemModel>
)
