package com.andy.github.home

import android.content.Context
import androidx.room.Room
import com.andy.github.home.data.local.SearchHistoryDao
import com.andy.github.home.data.local.SearchHistoryDatabase
import com.andy.github.home.di.DatabaseModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
object FakeDatabaseModule {

    @Provides
    @Singleton
    fun provideInMemoryDb(@ApplicationContext context: Context): SearchHistoryDatabase {
        return Room.inMemoryDatabaseBuilder(
            context, SearchHistoryDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun provideSearchHistoryDao(database: SearchHistoryDatabase): SearchHistoryDao {
        return database.searchHistoryDao()
    }
}