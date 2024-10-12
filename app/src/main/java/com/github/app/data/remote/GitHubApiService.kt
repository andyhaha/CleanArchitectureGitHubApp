package com.github.app.data.remote

import com.andy.network.data.ApiResult
import com.github.app.data.model.ApiUserResult
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApiService {

    @GET("search/users")
    suspend fun searchUserRepositories(
        @Query("q") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): ApiResult<ApiUserResult>
}