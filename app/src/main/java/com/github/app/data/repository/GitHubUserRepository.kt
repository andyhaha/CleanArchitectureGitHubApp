package com.github.app.data.repository

import com.andy.network.data.ApiResult
import com.andy.network.domain.Result
import com.github.app.data.model.toDomainUser
import com.github.app.data.remote.GitHubApiService
import com.github.app.domain.model.User
import com.github.app.domain.repository.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Named

class GitHubUserRepository @Inject constructor(
    private val apiService: GitHubApiService,
    @Named("io")
    private val dispatcher: CoroutineDispatcher
) : HomeRepository {
    override fun searchUserRepositories(): Flow<Result<List<User>>> {
        return flow {
            when (val result = apiService.searchUserRepositories()) {
                is ApiResult.Success -> {
                    val list = result.data.map {
                        it.toDomainUser()
                    }
                    emit(
                        Result.Success(list)
                    )
                }

                is ApiResult.Error -> {
                    emit(
                        Result.Error(
                            result.code,
                            result.message
                        )
                    )
                }

                is ApiResult.Exception -> {
                    emit(
                        Result.Failure(result.throwable)
                    )
                }
            }
        }.catch {
            emit(
                Result.Failure(it)
            )
        }.flowOn(dispatcher)
    }
}