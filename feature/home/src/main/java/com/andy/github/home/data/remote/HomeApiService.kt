package com.andy.github.home.data.remote

import com.andy.network.data.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApiService {

    @GET("search/users")
    suspend fun searchUserRepositories(
        @Query("q") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): ApiResult<ApiSimpleUserResponse>
}