package com.github.app.data.repository

import com.andy.network.data.ApiResult
import com.andy.network.domain.Result
import com.github.app.data.model.toDomainModel
import com.github.app.data.model.toDomainUser
import com.github.app.data.remote.GitHubApiService
import com.github.app.domain.model.Repository
import com.github.app.domain.model.User
import com.github.app.domain.repository.UserDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Named

class GitHubUserDetailsRepository @Inject constructor(
    private val apiService: GitHubApiService,
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