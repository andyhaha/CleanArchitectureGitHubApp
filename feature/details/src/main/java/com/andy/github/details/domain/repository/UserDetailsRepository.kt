package com.andy.github.details.domain.repository

import com.andy.github.details.domain.model.Repository
import com.andy.github.details.domain.model.User
import kotlinx.coroutines.flow.Flow
import com.andy.network.domain.Result

interface UserDetailsRepository {
    fun getUser(username: String): Flow<Result<User>>

    fun getUserRepositories(username: String): Flow<Result<List<Repository>>>
}