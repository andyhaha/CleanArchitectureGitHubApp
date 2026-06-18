package com.andy.github.details.data.repository

import com.andy.github.details.data.model.toDomainModel
import com.andy.github.details.data.model.toDomainUser
import com.andy.github.details.data.remote.DetailsApiService
import com.andy.github.details.domain.model.Repository
import com.andy.github.details.domain.model.User
import com.andy.github.details.domain.repository.UserDetailsRepository
import com.andy.network.data.ApiResult
import com.andy.network.data.map
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Named

class GitHubUserDetailsRepository @Inject constructor(
    private val apiService: DetailsApiService,
    @Named("io")
    private val dispatcher: CoroutineDispatcher,
) : UserDetailsRepository {

    override fun getUser(username: String): Flow<ApiResult<User>> {
        return flow {
            emit(apiService.getUser(username).map { it.toDomainUser() })
        }.flowOn(dispatcher)
    }

    override fun getUserRepositories(username: String): Flow<ApiResult<List<Repository>>> {
        return flow {
            val query = "user:$username"
            emit(apiService.getUserRepositories(query).map { it.items.map { item -> item.toDomainModel() } })
        }.flowOn(dispatcher)
    }
}