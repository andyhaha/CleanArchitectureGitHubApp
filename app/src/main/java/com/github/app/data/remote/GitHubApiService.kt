package com.github.app.data.remote

import com.andy.network.data.ApiResult
import com.github.app.data.model.ApiUserResult
import retrofit2.http.GET

interface GitHubApiService {
    @GET("user")
    suspend fun searchUserRepositories(): ApiResult<List<ApiUserResult>>
}