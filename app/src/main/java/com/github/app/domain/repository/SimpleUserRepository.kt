package com.github.app.domain.repository

import androidx.paging.PagingData
import com.github.app.domain.model.SimpleUser
import kotlinx.coroutines.flow.Flow

interface SimpleUserRepository {

    fun searchUserRepositories(
        query: String
    ): Flow<PagingData<SimpleUser>>
}