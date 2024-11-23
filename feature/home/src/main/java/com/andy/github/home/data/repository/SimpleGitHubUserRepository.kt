package com.andy.github.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.andy.github.home.data.paging.SearchPagingSource
import com.andy.github.home.data.remote.HomeApiService
import com.andy.github.home.domain.model.SimpleUser
import com.andy.github.home.domain.repository.SimpleUserRepository
import com.andy.network.common.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SimpleGitHubUserRepository @Inject constructor(
    private val apiService: HomeApiService
) : SimpleUserRepository {

    override fun searchUserRepositories(
        query: String
    ): Flow<PagingData<SimpleUser>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.PAGE_SIZE),
            pagingSourceFactory = {
                SearchPagingSource(
                    apiService = apiService,
                    query = query
                )
            }
        ).flow
    }
}