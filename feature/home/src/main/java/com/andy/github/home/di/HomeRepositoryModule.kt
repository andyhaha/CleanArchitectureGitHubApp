package com.andy.github.home.di

import com.andy.github.home.data.repository.DatabaseSearchHistoryRepository
import com.andy.github.home.domain.repository.SearchHistoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeRepositoryModule {

    @Binds
    abstract fun bindSearchHistoryRepository(
        repository: DatabaseSearchHistoryRepository,
    ): SearchHistoryRepository
}