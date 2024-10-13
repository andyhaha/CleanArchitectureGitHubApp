package com.andy.github.home.di

import android.content.Context
import androidx.room.Room
import com.andy.github.home.data.local.SearchHistoryDao
import com.andy.github.home.data.local.SearchHistoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideSearchHistoryDatabase(
        @ApplicationContext appContext: Context
    ): SearchHistoryDatabase {
        return Room.databaseBuilder(
            appContext,
            SearchHistoryDatabase::class.java,
            "search_history"
        ).build()
    }

    @Singleton
    @Provides
    fun provideSearchHistoryDao(database: SearchHistoryDatabase): SearchHistoryDao {
        return database.searchHistoryDao()
    }
}
