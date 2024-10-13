package com.github.app.domain.repository

import com.andy.network.domain.Result
import com.github.app.domain.model.Repository
import com.github.app.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserDetailsRepository {
    fun getUser(username: String): Flow<Result<User>>

    fun getUserRepositories(username: String): Flow<Result<List<Repository>>>
}