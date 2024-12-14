package com.andy.github.home.ui

import com.andy.github.home.data.FakeSearchHistoryRepository
import com.andy.github.home.domain.model.SearchItem
import com.andy.github.home.domain.model.toUiSearchItem
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class SearchHistoryViewModelTest {
    private lateinit var repository: FakeSearchHistoryRepository
    private lateinit var viewModel: SearchHistoryViewModel

    private val testDispatcher: TestDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = FakeSearchHistoryRepository()
        viewModel = SearchHistoryViewModel(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testAddHistoryItem() = runTest {
        val content = "New History"

        // Ensure initial state is Loading before adding item
        assertThat(viewModel.viewState.value)
            .isEqualTo(SearchUiState.Loading)

        // Add item via ViewModel
        viewModel.addHistoryItem(content)
//        repository.getAllSearchRecords().first()

        // Wait for any pending coroutines to complete
        advanceUntilIdle()

        // Check if the repository's state reflects the added item
        val state = viewModel.viewState.value
        assertThat(state)
            .isEqualTo(
                SearchUiState.Success(
                    historyItems = listOf(UiSearchItem(content = content))
                )
            )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDeleteHistoryItem() = runTest {
        val content = UiSearchItem(content = "Delete History")

        // Add item first
        repository.insertSearch(content.toDomainSearchItem())

        // Ensure initial state is Loading before deleting
        assertThat(viewModel.viewState.value).isEqualTo(SearchUiState.Loading)

        // Delete item via ViewModel
        viewModel.deleteHistoryItem(content)

        // Wait for any pending coroutines to complete
        advanceUntilIdle()

        // Check if the repository's state reflects the removal of the item
        val state = viewModel.viewState.first()
        assertThat(state).isEqualTo(SearchUiState.Success(historyItems = emptyList()))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testClearHistory() = runTest {
        val content = SearchItem(content = "Clear History")

        // Add an item first
        repository.insertSearch(content)

        // Ensure initial state is Loading before clearing history
        assertThat(viewModel.viewState.first()).isEqualTo(SearchUiState.Loading)

        // Clear history via ViewModel
        viewModel.clearHistory()

        // Wait for any pending coroutines to complete
        advanceUntilIdle()

        // Check if the repository's state is empty after clearing
        val state = viewModel.viewState.first()
        assertThat(state).isEqualTo(SearchUiState.Success(historyItems = emptyList()))
    }
}