package com.github.app.data.repository

import com.github.app.data.local.SearchHistoryDao
import com.github.app.data.local.entity.asDomainModel
import com.github.app.domain.model.SearchItem
import com.github.app.domain.model.asEntity
import com.github.app.domain.repository.SearchHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DatabaseSearchHistoryRepository @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao,
) : SearchHistoryRepository {
    override fun getAllSearchRecords(): Flow<List<SearchItem>> {
        return searchHistoryDao.getAllSearchRecords().map { list ->
            list.map { it.asDomainModel() }
        }
    }

    override suspend fun insertSearch(record: SearchItem) {
        searchHistoryDao.insertSearch(record.asEntity())
    }

    override suspend fun deleteSearch(content: SearchItem) {
        searchHistoryDao.deleteSearch(content.asEntity())
    }

    override suspend fun deleteAllSearchRecords() {
        searchHistoryDao.deleteAllSearchRecords()
    }
}