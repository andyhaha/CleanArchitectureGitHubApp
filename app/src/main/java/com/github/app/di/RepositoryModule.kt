package com.github.app.di

import com.github.app.data.repository.DatabaseSearchHistoryRepository
import com.github.app.data.repository.GitHubUserDetailsRepository
import com.github.app.data.repository.SimpleGitHubUserRepository
import com.github.app.domain.repository.SimpleUserRepository
import com.github.app.domain.repository.SearchHistoryRepository
import com.github.app.domain.repository.UserDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSimpleRepository(
        repository: SimpleGitHubUserRepository,
    ): SimpleUserRepository

    @Binds
    abstract fun bindSearchHistoryRepository(
        repository: DatabaseSearchHistoryRepository,
    ): SearchHistoryRepository

    @Binds
    abstract fun bindUserDetailsRepository(
        repository: GitHubUserDetailsRepository,
    ): UserDetailsRepository

}