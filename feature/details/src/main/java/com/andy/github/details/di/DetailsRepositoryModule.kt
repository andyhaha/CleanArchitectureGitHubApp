package com.andy.github.details.di

import com.andy.github.details.data.repository.GitHubUserDetailsRepository
import com.andy.github.details.domain.repository.UserDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailsRepositoryModule {

    @Binds
    abstract fun bindUserDetailsRepository(
        repository: GitHubUserDetailsRepository,
    ): UserDetailsRepository

}