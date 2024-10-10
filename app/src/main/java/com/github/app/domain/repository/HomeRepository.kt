package com.github.app.domain.repository

import com.andy.network.domain.Result
import com.github.app.domain.model.User
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun searchUserRepositories(): Flow<Result<List<User>>>
}