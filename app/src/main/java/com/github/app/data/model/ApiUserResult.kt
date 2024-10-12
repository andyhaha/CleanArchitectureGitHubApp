package com.github.app.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiUserResult(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<ApiUserModel>
)