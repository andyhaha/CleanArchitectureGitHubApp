package com.andy.github.home.ui

import com.andy.github.home.data.FakeSearchHistoryRepository
import com.andy.github.home.domain.model.SearchItem
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class SearchHistoryViewModelTest {
    private lateinit var viewModel: SearchHistoryViewModel
    private val testDispatcher: TestDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SearchHistoryViewModel(FakeSearchHistoryRepository())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // Helper to assert the Loading state
    private suspend fun assertLoadingState() {
        val loadingState = viewModel.viewState.first { it is SearchUiState.Loading }
        assertThat(loadingState)
            .isEqualTo(SearchUiState.Loading)
    }

    // Helper to assert the Success state with expected items
    private suspend fun assertSuccessState(expectedItems: List<SearchItem>) {
        val successState =
            viewModel.viewState.first { it is SearchUiState.Success } as SearchUiState.Success
        assertThat(
            successState.historyItems.map { it.toDomainSearchItem() })
            .isEqualTo(expectedItems)
    }

    @Test
    fun testAddHistoryItem() = runTest {
        val content = "New History"
        val expectedItem = SearchItem(content = content)

        // Add item via ViewModel
        viewModel.addHistoryItem(content)

        // Assert Loading and Success states
        assertLoadingState()
        assertSuccessState(listOf(expectedItem))
    }

    @Test
    fun testDeleteHistoryItem() = runTest {
        val content = "Delete History"
        val uiItem = UiSearchItem(content = content)

        // Add an item first
        viewModel.addHistoryItem(content)

        // Assert Loading state before deletion
        assertLoadingState()

        // Delete the item
        viewModel.deleteHistoryItem(uiItem)

        // Assert Success state reflects an empty list
        assertSuccessState(emptyList())
    }

    @Test
    fun testClearHistory() = runTest {
        val content = "Clear History"

        // Add an item first
        viewModel.addHistoryItem(content)

        // Assert Loading state before clearing
        assertLoadingState()

        // Clear all history
        viewModel.clearHistory()

        // Assert Success state reflects an empty list
        assertSuccessState(emptyList())
    }

    @Test
    fun testClearHistoryWithMultipleItems() = runTest {
        // Add some items to the history
        val items = listOf(
            SearchItem(content = "History 1"),
            SearchItem(content = "History 2"),
            SearchItem(content = "History 3")
        )

        items.forEach { viewModel.addHistoryItem(it.content) }

        // Trigger clear history operation
        viewModel.clearHistory()

        // Assert that the history is now empty
        assertSuccessState(emptyList())
    }
}