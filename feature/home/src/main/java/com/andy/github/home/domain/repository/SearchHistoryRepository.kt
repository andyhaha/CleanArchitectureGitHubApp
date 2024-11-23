package com.andy.github.home.domain.repository

import com.andy.github.home.domain.model.SearchItem
import kotlinx.coroutines.flow.Flow

interface SearchHistoryRepository {
    fun getAllSearchRecords(): Flow<List<SearchItem>>

    suspend fun insertSearch(record: SearchItem)

    suspend fun deleteSearch(content: SearchItem)

    suspend fun deleteAllSearchRecords()
}