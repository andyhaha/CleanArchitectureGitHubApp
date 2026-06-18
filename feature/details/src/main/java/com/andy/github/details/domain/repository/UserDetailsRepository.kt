package com.andy.github.details.domain.repository

import com.andy.github.details.domain.model.Repository
import com.andy.github.details.domain.model.User
import kotlinx.coroutines.flow.Flow
import com.andy.network.data.ApiResult

interface UserDetailsRepository {
    fun getUser(username: String): Flow<ApiResult<User>>

    fun getUserRepositories(username: String): Flow<ApiResult<List<Repository>>>
}