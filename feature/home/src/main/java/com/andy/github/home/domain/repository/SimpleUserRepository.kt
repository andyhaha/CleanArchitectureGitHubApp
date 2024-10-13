package com.andy.github.home.domain.repository

import androidx.paging.PagingData
import com.andy.github.home.domain.model.SimpleUser
import kotlinx.coroutines.flow.Flow

interface SimpleUserRepository {

    fun searchUserRepositories(
        query: String
    ): Flow<PagingData<SimpleUser>>
}