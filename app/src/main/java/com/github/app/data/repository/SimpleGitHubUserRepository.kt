package com.github.app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.app.common.Constants
import com.github.app.data.paging.SearchPagingSource
import com.github.app.data.remote.GitHubApiService
import com.github.app.domain.model.SimpleUser
import com.github.app.domain.repository.SimpleUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SimpleGitHubUserRepository @Inject constructor(
    private val apiService: GitHubApiService
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