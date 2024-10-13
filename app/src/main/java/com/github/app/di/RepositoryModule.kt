//package com.github.app.di
//
//import com.andy.github.home.data.repository.DatabaseSearchHistoryRepository
//import com.andy.github.details.data.repository.GitHubUserDetailsRepository
//import com.andy.github.home.data.repository.SimpleGitHubUserRepository
//import com.andy.github.home.domain.repository.SimpleUserRepository
//import com.andy.github.home.domain.repository.SearchHistoryRepository
//import com.andy.github.details.domain.repository.UserDetailsRepository
//import dagger.Binds
//import dagger.Module
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//
//@Module
//@InstallIn(SingletonComponent::class)
//abstract class RepositoryModule {
//
//    @Binds
//    abstract fun bindSimpleRepository(
//        repository: SimpleGitHubUserRepository,
//    ): SimpleUserRepository
//
//    @Binds
//    abstract fun bindSearchHistoryRepository(
//        repository: DatabaseSearchHistoryRepository,
//    ): SearchHistoryRepository
//
//    @Binds
//    abstract fun bindUserDetailsRepository(
//        repository: GitHubUserDetailsRepository,
//    ): UserDetailsRepository
//
//}