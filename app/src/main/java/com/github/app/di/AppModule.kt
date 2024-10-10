package com.github.app.di

import com.github.app.data.remote.GitHubApiService
import com.github.app.data.repository.GitHubUserRepository
import com.github.app.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): GitHubApiService {
        return retrofit.create(GitHubApiService::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideHomeRepository(
//        apiService: GitHubApiService,
//        @Named("io")
//        dispatcher: CoroutineDispatcher
//    ): HomeRepository {
//        return GitHubUserRepository(apiService, dispatcher);
//    }

    @Provides
    @Singleton
    @Named("io")
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    @Named("main")
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
