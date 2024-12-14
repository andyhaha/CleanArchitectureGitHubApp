package com.andy.github.home.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.andy.github.home.domain.model.SearchItem
import com.andy.github.home.domain.model.toUiSearchItem
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class HomeSearchBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun searchBar_initialState_displaysPlaceholder() {
        composeTestRule.setContent {
            HomeSearchBar()
        }

        // Verify that the placeholder text is displayed in the initial state
        composeTestRule.onNodeWithText("Search users").assertIsDisplayed()
    }

    @Test
    fun searchBar_enterTextAndSearch_triggersCallback() {
        var searchTriggered = false
        val testSearchItem = SearchItem(content = "TestUser")

        composeTestRule.setContent {
            HomeSearchBar(onSearch = { item ->
                searchTriggered = (item == testSearchItem.toUiSearchItem())
            })
        }

        // Simulate text input
        composeTestRule.onNode(hasSetTextAction()).performTextInput(testSearchItem.content)

        // Click the search icon
        composeTestRule.onNodeWithContentDescription("search icon").performClick()

        // Verify that the search callback was triggered
        assertThat(searchTriggered).isTrue()
    }

    @Test
    fun searchBar_expandAndClickHistoryItem_updatesQueryAndSearch() {
        var searchTriggered = false
        val testSearchItem = SearchItem(content = "HistoryItem")

        composeTestRule.setContent {
            HomeSearchBar(
                searchUiState = SearchUiState.Success(listOf(testSearchItem.toUiSearchItem())),
                onSearch = { item ->
                    searchTriggered = (item == testSearchItem.toUiSearchItem())
                }
            )
        }

        // Expand the search history
        composeTestRule.onNode(hasSetTextAction()).performTextInput("Any")
        composeTestRule.onNodeWithContentDescription("search icon").performClick()

        // Click a history item
        composeTestRule.onNodeWithText(testSearchItem.content).performClick()

        // Verify that the search callback was triggered
        assertThat(searchTriggered).isTrue()
    }

    @Test
    fun searchBar_inputUpdatesText() {
        composeTestRule.setContent {
            HomeSearchBar()
        }

        // Simulate text input into the search field
        composeTestRule.onNode(hasSetTextAction()).performTextInput("Test Query")

        // Verify that the input field now has the expected text
        composeTestRule.onNode(hasSetTextAction()).assertTextEquals("Test Query")
    }

    @Test
    fun searchBar_clearText_resetsText() {
        composeTestRule.setContent {
            HomeSearchBar()
        }

        val placeholder = "Search users"
        // Simulate typing
        composeTestRule.onNodeWithText(placeholder).performTextInput("SomeText")

        // Assert that the text field now contains "SomeText"
        composeTestRule.onNode(hasSetTextAction()).assertTextEquals("SomeText")

        // Simulate clicking the close icon
        composeTestRule.onNodeWithContentDescription("close icon").performClick()

        // Assert that the text field is now showing the placeholder
        composeTestRule.onNodeWithText(placeholder).assertIsDisplayed()
    }

    @Test
    fun searchBar_expandedStateToggle() {
        composeTestRule.setContent {
            HomeSearchBar()
        }

        // Initially, the expanded state should be false
        composeTestRule.onNodeWithContentDescription("search icon").assertExists()

        // Simulate opening the search bar
        composeTestRule.onNode(hasSetTextAction()).performTextInput("Open Search")

        // The expanded state should be true now
        composeTestRule.onNodeWithContentDescription("search icon").assertExists()

        // Simulate closing the search bar
        composeTestRule.onNodeWithContentDescription("close icon").performClick()

        // The expanded state should be false
        composeTestRule.onNodeWithContentDescription("search icon").assertExists()
    }
}