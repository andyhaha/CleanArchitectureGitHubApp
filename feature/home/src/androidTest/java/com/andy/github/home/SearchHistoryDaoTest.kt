package com.andy.github.home

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.andy.github.home.data.local.SearchHistoryDao
import com.andy.github.home.data.local.SearchHistoryDatabase
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
    fun test_insert_a_search_record() = runTest {
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
    fun test_delete_a_search_record() = runTest {
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
    fun test_delete_all_search_records() = runTest {
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
}