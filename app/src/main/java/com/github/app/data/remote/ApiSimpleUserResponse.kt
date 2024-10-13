package com.github.app.data.remote

import com.github.app.data.model.ApiSimpleUserModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiSimpleUserResponse(
    @Json(name = "total_count")
    val totalCount: Int,
    val items: List<ApiSimpleUserModel>
)