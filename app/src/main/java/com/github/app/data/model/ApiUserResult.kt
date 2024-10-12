package com.github.app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiUserResult(
    @Json(name = "total_count")
    val totalCount: Int,
    val items: List<ApiUserModel>
)