package com.andy.github.details.di

import com.andy.github.details.data.remote.DetailsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailsModule {

    @Provides
    @Singleton
    fun provideDetailsApiService(retrofit: Retrofit): DetailsApiService {
        return retrofit.create(DetailsApiService::class.java)
    }
}
