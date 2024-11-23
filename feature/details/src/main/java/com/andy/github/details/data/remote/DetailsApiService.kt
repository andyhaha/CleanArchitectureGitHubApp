package com.andy.github.details.data.remote

import com.andy.github.details.data.model.ApiUserResult
import com.andy.network.data.ApiResult

interface DetailsApiService {

    @retrofit2.http.GET("users/{username}")
    suspend fun getUser(@retrofit2.http.Path("username") username: String): ApiResult<ApiUserResult>

    /**
     * q=user:{username} for example q=user:andy
     */
    @retrofit2.http.GET("search/repositories")
    suspend fun getUserRepositories(
        @retrofit2.http.Query("q") query: String
    ): ApiResult<ApiRepositoriesResponse>
}