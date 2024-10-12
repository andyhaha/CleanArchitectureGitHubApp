package com.github.app.domain.repository

import androidx.paging.PagingData
import com.github.app.domain.model.User
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun searchUserRepositories(
        query: String
    ): Flow<PagingData<User>>
}