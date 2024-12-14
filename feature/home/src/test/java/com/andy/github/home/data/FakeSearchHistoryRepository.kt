package com.andy.github.home.data

import com.andy.github.home.domain.model.SearchItem
import com.andy.github.home.domain.repository.SearchHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeSearchHistoryRepository : SearchHistoryRepository {

    // Use a MutableStateFlow to simulate changes in the data
    private val searchItems = MutableStateFlow<List<SearchItem>>(emptyList())

    override fun getAllSearchRecords(): Flow<List<SearchItem>> {
        return searchItems
    }

    override suspend fun insertSearch(record: SearchItem) {
        // Simulate adding the search record to the list
        val currentItems = searchItems.value.toMutableList()
        currentItems.add(record)
        searchItems.emit(currentItems)
    }

    override suspend fun deleteSearch(content: SearchItem) {
        // Simulate deleting the search record from the list
        val currentItems = searchItems.value.toMutableList()
        currentItems.remove(content)
        searchItems.emit(currentItems)
    }

    override suspend fun deleteAllSearchRecords() {
        // Simulate clearing all search records
        searchItems.emit(emptyList())
    }
}