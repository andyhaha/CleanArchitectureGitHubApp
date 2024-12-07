package com.andy.github.home.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.andy.github.home.data.local.entity.SearchEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class SearchHistoryDaoTest {
    private lateinit var database: SearchHistoryDatabase

    private lateinit var searchHistoryDao: SearchHistoryDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SearchHistoryDatabase::class.java
        ).build()
        searchHistoryDao = database.searchHistoryDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertSingleSearchRecord() = runTest {
        // Insert a search record
        val searchEntity = SearchEntity(
            id = 1,
            content = "test insert"
        )
        searchHistoryDao.insertSearch(searchEntity)

        // Then check that the record is in the database
        val searchRecords = searchHistoryDao.getAllSearchRecords()
            .first()

        // Assert that the search records contains the inserted record
        assertThat(searchRecords).contains(searchEntity)
    }

    @Test
    fun deleteSingleSearchRecord() = runTest {
        // Insert a search record
        val searchEntity = SearchEntity(
            id = 1,
            content = "test delete one record"
        )
        searchHistoryDao.insertSearch(searchEntity)

        // Then delete the record
        searchHistoryDao.deleteSearch(searchEntity)
        val searchRecords = searchHistoryDao.getAllSearchRecords()
            .first()

        assertThat(searchRecords).doesNotContain(searchEntity)
    }

    @Test
    fun deleteAllSearchRecords() = runTest {
        // Insert multiple search records
        val insertList = mutableListOf<SearchEntity>()
        repeat(10) {
            val searchEntity = SearchEntity(
                id = it + 1,
                content = "test delete all record $it"
            )
            insertList.add(searchEntity)
        }

        // Insert all records
        insertList.forEach {
            searchHistoryDao.insertSearch(it)
        }

        // Then delete all records
        searchHistoryDao.deleteAllSearchRecords()

        val searchRecords = searchHistoryDao.getAllSearchRecords()
            .first()

        assertThat(searchRecords).isEmpty()
    }

    @Test
    fun insertAndRetrieveMultipleRecords() = runTest {
        val searchEntities = (1..3).map {
            SearchEntity(
                id = it,
                content = "test multiple $it"
            )
        }
        searchEntities.forEach { searchHistoryDao.insertSearch(it) }

        val searchRecords = searchHistoryDao.getAllSearchRecords().first()
        assertThat(searchRecords).hasSize(3)
        assertThat(searchRecords).containsAtLeastElementsIn(searchEntities)
    }

    @Test
    fun retrieveEmptyDatabase() = runTest {
        val searchRecords = searchHistoryDao.getAllSearchRecords().first()
        assertThat(searchRecords).isEmpty()
    }
}