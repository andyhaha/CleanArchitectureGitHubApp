package com.github.app.data.remote

import com.andy.network.data.ApiResult
import com.github.app.data.model.ApiUserResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiService {

    @GET("search/users")
    suspend fun searchUserRepositories(
        @Query("q") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): ApiResult<ApiSimpleUserResponse>

    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): ApiResult<ApiUserResult>

    /**
     * q=user:{username} for example q=user:andy
     */
    @GET("search/repositories")
    suspend fun getUserRepositories(
        @Query("q") query: String
    ): ApiResult<ApiRepositoriesResponse>
}