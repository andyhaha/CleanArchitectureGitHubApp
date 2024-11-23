package com.andy.github.home.data.repository

import com.andy.github.home.data.local.SearchHistoryDao
import com.andy.github.home.data.local.entity.asDomainModel
import com.andy.github.home.domain.model.SearchItem
import com.andy.github.home.domain.model.asEntity
import com.andy.github.home.domain.repository.SearchHistoryRepository
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