package com.andy.github.details.data.repository

import com.andy.github.details.data.model.toDomainModel
import com.andy.github.details.data.model.toDomainUser
import com.andy.github.details.data.remote.DetailsApiService
import com.andy.github.details.domain.model.Repository
import com.andy.github.details.domain.model.User
import com.andy.github.details.domain.repository.UserDetailsRepository
import com.andy.network.data.ApiResult
import com.andy.network.domain.Result
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

    override fun getUser(username: String): Flow<Result<User>> {
        return flow {
            when (val result = apiService.getUser(username)) {
                is ApiResult.Success -> {
                    emit(Result.Success(result.data.toDomainUser()))
                }
                is ApiResult.Error -> {
                    emit(Result.Error(result.code, result.message))
                }
                is ApiResult.Exception -> {
                    emit(Result.Failure(result.throwable))
                }
            }
        }.flowOn(dispatcher)
    }

    override fun getUserRepositories(username: String): Flow<Result<List<Repository>>> {
        return flow {
            val query = "user:$username"
            when (val result = apiService.getUserRepositories(query)) {
                is ApiResult.Success -> {
                    val list = result.data.items.map {
                        it.toDomainModel()
                    }
                    emit(Result.Success(list))
                }
                is ApiResult.Error -> {
                    emit(Result.Error(result.code, result.message))
                }
                is ApiResult.Exception -> {
                    emit(Result.Failure(result.throwable))
                }
            }
        }.flowOn(dispatcher)
    }
}