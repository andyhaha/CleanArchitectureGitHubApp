package com.andy.github.home.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.andy.github.home.domain.model.SearchItem
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class SearchHistoryTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoadingState() {
        composeTestRule.setContent {
            SearchHistory(state = SearchUiState.Loading)
        }

        // Verify that the "Loading..." text is shown when in loading state
        composeTestRule.onNodeWithText("Loading...").assertExists()
    }

    @Test
    fun testSuccessState() {
        val sampleHistoryItems = listOf(
            SearchItem(content = "Item 1"),
            SearchItem(content = "Item 2")
        )

        composeTestRule.setContent {
            SearchHistory(state = SearchUiState.Success(sampleHistoryItems))
        }

        // Verify that items "Item 1" and "Item 2" are displayed
        composeTestRule.onNodeWithText("Item 1").assertExists()
        composeTestRule.onNodeWithText("Item 2").assertExists()
    }

    @Test
    fun testErrorState() {
        // Assuming the error state is handled to display an error message or UI
        composeTestRule.setContent {
            SearchHistory(state = SearchUiState.Error("An error occurred"))
        }

        // Verify if the error message appears in the UI
        composeTestRule.onNodeWithText("An error occurred").assertExists()
    }

    @Test
    fun testItemClick() {
        val sampleHistoryItems = listOf(
            SearchItem(content = "Item 1")
        )

        var clickedItem: SearchItem? = null
        val onItemClick: (SearchItem) -> Unit = { clickedItem = it }

        composeTestRule.setContent {
            SearchHistory(state = SearchUiState.Success(sampleHistoryItems), onItemClick = onItemClick)
        }

        composeTestRule.onNodeWithText("Item 1").performClick()

        // Verify that the clicked item is passed to the onItemClick callback
        assertThat(clickedItem).isNotNull()
        assertThat(clickedItem?.content).isEqualTo("Item 1")
    }

    @Test
    fun testItemDelete() {
        val sampleHistoryItems = listOf(
            SearchItem(content = "Item 1")
        )

        var deletedItem: SearchItem? = null
        val delete: (SearchItem) -> Unit = { deletedItem = it }

        composeTestRule.setContent {
            SearchHistory(state = SearchUiState.Success(sampleHistoryItems), delete = delete)
        }

        composeTestRule.onNodeWithContentDescription("Right Icon").performClick()

        // Verify that the delete callback was triggered with the correct item
        assertThat(deletedItem).isNotNull()
        assertThat(deletedItem?.content).isEqualTo("Item 1")
    }
}